package com.yanzi.taurus.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.service.impl.CUserServiceImpl;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.enums.SMSVerifiCodeType;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PhoneService;
import com.yanzi.taurus.service.UserService;

@Service
public class UserServiceImpl extends CUserServiceImpl implements UserService{

    @Autowired
    private SMSServiceImpl smsService;
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        super.saveUserInfo(userInfo);
        userMapper.insertOrUpdateUserInfo(userInfo);
    }

    @Override
    public long resetPassword(String phoneNo, String password, String userVerifiCode) {
        // judge phone is register
        AccountInfo accountInfo = phoneService.isRegisted(phoneNo);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PASSWORD);
        accountInfo.setPassword(password);
        userMapper.updateAccountInfo(accountInfo);
        return accountInfo.getId();
    }
    
    @Override
    public void modifyPhoneNo(long userId, String phoneNo, String userVerifiCode) {
        phoneService.isNotRegisted(phoneNo);
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PHONENO);
        accountInfo.setPhoneNo(phoneNo);
        userMapper.updateAccountInfo(accountInfo);
    }
    
    @Override
    public void modifyPassword(long userId, String newPassword, String oldPassword) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        if (!accountInfo.getPassword().equals(oldPassword)) {
            throw new CommonException(ReturnCode.PASSWORD_ERROR);
        }
        accountInfo.setPassword(newPassword);
        userMapper.updateAccountInfo(accountInfo);
    }

    @Override
    public void addUserThirdPartyInfo(long userId, ThirdPartyInfo thirdPartyInfo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByThirdPartyInfo(thirdPartyInfo);
        if (null != accountInfo) {
            throw new CommonException(ReturnCode.THIRD_PARTY_ID_IS_BINDED);
        }
        thirdPartyInfo.setUserId(userId);
        userMapper.insertOrUpdateThirdPartyInfo(thirdPartyInfo);
    }
    
    @Override
    public void bindingPhoneNo(long userId, String phoneNo, String userVerifiCode,
            String password) {
        phoneService.isNotRegisted(phoneNo);
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PHONENO);
        accountInfo.setPhoneNo(phoneNo);
        accountInfo.setPassword(password);
        userMapper.updateAccountInfo(accountInfo);
    }
    
    @Override
    public AccountInfo getAccountInfoByUserId(long userId){
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        if(null == accountInfo) {
            return AccountInfo.DEFAULT;
        }
        return accountInfo;
    }

    @Override
    public List<ThirdPartyInfo> getThirdPartyInfoByUserId(long userId) {
        List<ThirdPartyInfo> thirdPartyInfos = userMapper.selectThirdPartyInfoByUserId(userId);
        if(null == thirdPartyInfos) {
            return Collections.emptyList();
        }
        return thirdPartyInfos;
    }

    public List<FeedbackInfo> loadUserFeedback(String token) {
        long userId = loadUserId(token);
        return userMapper.selectFeedbackByUserId(userId);
    }
    /**
     * 添加留言
     * @author dusc
     * @param userId
     * @param message
     */
    public void addUserFeedback(long userId,String message){
    	userMapper.addFeedback(userId,message);
    }
    
    /**
     * 模糊查询好友
     * @param userId
     * @param nickName
     * @author dusc
     */
    public List<UserInfo> fetchFriends(long userId,String nickName){
    	return userMapper.fetchFriends(userId, nickName);
    }
}
