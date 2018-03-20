package com.yanzi.taurus.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.DESCryptoUtils;
import com.yanzi.common.utils.HttpClientUtils;
import com.yanzi.common.utils.HttpClientUtils.HttpRequestType;
import com.yanzi.common.utils.MD5Utils;
import com.yanzi.common.utils.TimeUtils;
import com.yanzi.taurus.entity.SMSVerifiCodeInfo;
import com.yanzi.taurus.enums.SMSVerifiCodeType;
import com.yanzi.taurus.mysql.SMSVerifiCodeMapper;
import com.yanzi.taurus.service.PhoneService;
import com.yanzi.taurus.service.SMSService;
import com.yanzi.taurus.utils.SMSUtils;

@Service
public class SMSServiceImpl implements SMSService, InitializingBean {
    private static final Logger logger = new MLogger(SMSServiceImpl.class);

    @Value("#{configProperties['MAX_VERIFI_MSG_NUM']}")
    private int MAX_VERIFI_MSG_NUM = 5;

    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final long VERIFI_MSG_VALID_TIME = 30 * 60 * 1000l;

    @Value("#{configProperties['sms.user']}")
    private String smsUser = "";
    @Value("#{configProperties['sms.password']}")
    private String smsPwd = "";
    @Value("#{configProperties['sms.url']}")
    private String smsUrl = "";
    @Value("#{configProperties['sms.userid']}")
    private String smsUserId = "";

    private String STAMP_TIME_FORMAT = "MMddHHmmss";

    @Autowired
    private SMSVerifiCodeMapper smsVerifiCodeMapper;
    @Autowired
    private HttpClientUtils httpClientUtils;
    @Autowired
    private PhoneService phoneService;

    private byte[] smsKeyBytes = new byte[8];

    @Override
    public void verificationCodeJudge(String phoneNo, String userVerifiCode,
            SMSVerifiCodeType type) {
        // get verifi Code
        SMSVerifiCodeInfo verifiCodeInfo = smsVerifiCodeMapper.selectByPhoneNoAndType(phoneNo,
                type.getType());
        if (verifiCodeInfo == null || !userVerifiCode.equals(verifiCodeInfo.getCode())) {
            throw new CommonException(ReturnCode.VERIFI_CODE_IS_ERROR);
        }
        if (0 != verifiCodeInfo.getValid()) {
            throw new CommonException(ReturnCode.SMS_VERIFI_CODE_INVALID);
        }
        long now = System.currentTimeMillis();
        if ((now - verifiCodeInfo.getAddTime().getTime()) > VERIFI_MSG_VALID_TIME) {
            throw new CommonException(ReturnCode.SMS_VALID_TIME_EXCEED);
        }
        // set code invalid
        smsVerifiCodeMapper.updateInvalid(verifiCodeInfo.getId());
    }

    @Override
    public long sendVerifiMessageRegister(String phoneNo) {
        phoneService.isNotRegisted(phoneNo);
        return sendVerifiMessage(phoneNo, SMSVerifiCodeType.USER_REGISTER);
    }

    @Override
    public long sendVerifiMessageModifyPhoneNo(String phoneNo, long userId) {
        phoneService.isNotRegisted(phoneNo);//不抛已登录的异常就没注册
        return sendVerifiMessage(phoneNo, SMSVerifiCodeType.RESET_PHONENO);
    }

    @Override
    public long sendVerifiMessageResetPassword(String phoneNo) {
        phoneService.isRegisted(phoneNo);
        return sendVerifiMessage(phoneNo, SMSVerifiCodeType.RESET_PASSWORD);
    }

    private long sendVerifiMessage(String phoneNo, SMSVerifiCodeType type) {
        // load sms count
        Timestamp beginTime = new Timestamp(TimeUtils.getTodayStartTime().getTime());
        Timestamp endTime = new Timestamp(TimeUtils.getTodayEndTime().getTime());
        int count = smsVerifiCodeMapper.selectCountByPhoneNoAndRangeTime(phoneNo, beginTime,
                endTime);    //短信验证码日记录条数
        if (count >= MAX_VERIFI_MSG_NUM) {
            throw new CommonException(ReturnCode.SMS_DAY_MAX_NUM_EXCEED);
        }
        String code = generateVerificationCode();
        sendVerifyMessage(phoneNo, code);
        SMSVerifiCodeInfo verifiCode = new SMSVerifiCodeInfo();
        verifiCode.setPhoneNo(phoneNo);
        verifiCode.setCode(code);
        verifiCode.setType(type.getType());
        smsVerifiCodeMapper.addNewRecord(verifiCode);
        return verifiCode.getId();
    }

    private void sendVerifyMessage(String phoneNo, String code) {
        Map<String, String> parameters = new HashMap<>();
        JSONObject params = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat(STAMP_TIME_FORMAT);
        params.put("UserName", smsUser);
        String stamp = format.format(new Date());
        params.put("Stamp", stamp);
        params.put("Secret", MD5Utils.getPwd(smsPwd + stamp).toUpperCase());
        params.put("Moblie", phoneNo);
        String content = String.format(SMSUtils.SMS_VERIFY_FORMAT, code);
        params.put("Text", content);
        params.put("Ext", "");
        params.put("SendTime", "");
        byte[] desBytes;
        try {
            desBytes = DESCryptoUtils.DES_CBC_Encrypt(params.toJSONString().getBytes("utf-8"),
                    this.smsKeyBytes);
        } catch (UnsupportedEncodingException e) {
            logger.warn("parse sms content failed! phone no is {}, content is {}", phoneNo,
                    content);
            throw new CommonException(ReturnCode.SMS_SEND_FAILED);
        }
        String text64 = Base64.encodeBase64String(desBytes).replaceAll("[\\s*\t\n\r]", "");
        parameters.put("UserId", smsUserId);
        parameters.put("Text64", text64);

        String response = httpClientUtils.getResponse(smsUrl, parameters, HttpRequestType.GET, 3);
        JSONObject jsonResponse = JSONObject.parseObject(response);
        if (1 != jsonResponse.getIntValue("StatusCode")) {
            throw new CommonException(ReturnCode.SMS_SEND_FAILED);
        }
    }

    private String generateVerificationCode() {
        String code = "";
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; ++i) {
            int randNum = (int) (Math.random() * 10);
            code += randNum;
        }
        return code;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keys = smsPwd.getBytes();
        System.arraycopy(keys, 0, smsKeyBytes, 0, keys.length > 8 ? 8 : keys.length);
    }

}
