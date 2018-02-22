package com.yanzi.taurus.controller;

import java.security.interfaces.RSAPrivateKey;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.utils.RSAEncrypt;
import com.yanzi.taurus.controller.params.LoginPhoneNoParams;
import com.yanzi.taurus.controller.params.LoginThirdPartyParams;
import com.yanzi.taurus.controller.params.LoginTokenParams;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.DeviceInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.service.LoginService;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.view.ViewLoginResponse;

@Controller
public class LoginController extends BaseController<ViewLoginResponse> implements InitializingBean {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    private RSAPrivateKey privateKey;

    @RequestMapping(value = "/login/phoneno", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loginByPhoneNo(@Valid LoginPhoneNoParams params) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(params.getDeviceId());
        AccountInfo accountInfo = loginService.loginByPhoneNo(params.getPhoneNo(),
                params.getPassword(), deviceInfo);
        ViewLoginResponse response = new ViewLoginResponse(accountInfo.getId(),
                accountInfo.getToken());
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/login/thirdparty", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loginByThirdPartyParams(
            @Valid LoginThirdPartyParams params) {
        String loginStr = new String(
                RSAEncrypt.decrypt(privateKey, Base64.decodeBase64(params.getParam())));
        ThirdPartyInfo thirdPartyInfo = JSON.parseObject(loginStr, ThirdPartyInfo.class);

        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(params.getDeviceId());
        AccountInfo accountInfo = loginService.loginByThirdPartyInfo(thirdPartyInfo, deviceInfo);
        ViewLoginResponse response = new ViewLoginResponse(accountInfo.getId(),
                accountInfo.getToken());
        return packageSuccessData(response);
    }
    @RequestMapping(value = "/bind/thirdparty", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> bindThirdPartInfo(
            @Valid LoginThirdPartyParams params) {
    	
    	long userId = userService.loadUserId(params.getToken());
        String loginStr = new String(
                RSAEncrypt.decrypt(privateKey, Base64.decodeBase64(params.getParam())));
        ThirdPartyInfo thirdPartyInfo = JSON.parseObject(loginStr, ThirdPartyInfo.class);

//        DeviceInfo deviceInfo = new DeviceInfo();
//        deviceInfo.setDeviceId(params.getDeviceId());
        loginService.bindThirdPartInfo(thirdPartyInfo, userId);
       
        return packageSuccessData(new ViewLoginResponse(userId,params.getToken()));
    }
    @RequestMapping(value = "/login/token", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loginByToken(@Valid LoginTokenParams params) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceId(params.getDeviceId());
        long userId = userService.loadUserIdNoCache(params.getToken());
        AccountInfo accountInfo = loginService.loginByUserId(userId, deviceInfo);
        ViewLoginResponse response = new ViewLoginResponse(accountInfo.getId(),
                accountInfo.getToken());
        return packageSuccessData(response);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        privateKey = RSAEncrypt.loadPrivateKeyByStr(
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVEdd3fjuFzjG1/1kX8qXpuIQV9KpTn1rtnFQiV51p3qytp2iB8Z+teFoN/z+4yg6aS3EB/1vHrIGVxtLlU/+T4+NDh5h5N/dlRuOV3iiNK/Et8xMrtejwvq9PuqX65yq8J2v9hLlBnQkS8kKhP/BY+51B+6fVbhX43Ay1dKlVPAgMBAAECgYBpUhCfPcoLaRyz54UBAvxqdmZ63gJV9M1GjnG8D/PpFlwyBXopu75qI4LLeJdlIDH/5JWSUSYE86eonmbiuQV9tSt/Hd7mpvxW+AXreK+ox6ztiYMg10tq/8K8UpvGjkMrRVUHb/STRUY8r3Kv/ZeFXJmxcOpNsVz3f2iQlU2FiQJBAP21mktp0XzlKIXnZNgOhvAV/TUmWPWUkFbxtO//lzYOcnkvgW0EndGkUI/ua1mCTVrvib0ojtn1mTh3VuUsJNUCQQCmwnE1XOt2SyfEqOW51swHToyp2MWMT1rbIBRIzL/vmsKKmY1hw9d8M2qnb8oU03dazQKoNYoCqhfOTOAP7/OTAkEApkkCqe7fOObRWoJA3EMZOf6PiOhrYfpPaEzfdHWm2+04Jil2wMdH0QHLM6rmfTIkFTfupSYSCtUn6ZR+RZJbSQJAEup3gQAbTX3U8v/dnyj4V9PXLOUD85iEy9plsqRXGUzKyIIGgZJ/fP0wGfIaUCZ0oX4j0QTRtN+qd6JMwEINtQJAFGMK7tBMRvW8tIW1F87NfLkBm8ygdkR+JWm+fdXT1o8D+97/ltOxIrE42CTef8N7UCK0axaXoA9RhKCi9R526w==");
    }
}
