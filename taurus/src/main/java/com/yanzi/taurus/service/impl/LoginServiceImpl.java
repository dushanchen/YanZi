package com.yanzi.taurus.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.exception.CommonException;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.DeviceInfo;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.LoginService;
import com.yanzi.taurus.service.RegisterService;
import com.yanzi.taurus.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;

    @Override
    public AccountInfo loginByPhoneNo(String phoneNo, String password, DeviceInfo deviceInfo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByPhoneNo(phoneNo);
        if(null == accountInfo) {
        	throw new CommonException(ReturnCode.PHONE_NO_IS_NOT_REGISTER);
        }
        if (!accountInfo.getPassword().equals(password)) {
            throw new CommonException(ReturnCode.PASSWORD_ERROR);
        }
        return this.login(accountInfo, deviceInfo);
    }

    @Override
    public AccountInfo loginByUserId(long userId, DeviceInfo deviceInfo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        return this.login(accountInfo, deviceInfo);
    }

    @Override
    public AccountInfo loginByThirdPartyInfo(ThirdPartyInfo thirdPartyInfo, DeviceInfo deviceInfo) {
        if (null == thirdPartyInfo || (StringUtils.isEmpty(thirdPartyInfo.getThirdPartyId()))) {
            throw new CommonException(ReturnCode.THIRD_PARTY_ID_IS_NULL);
        }
        AccountInfo accountInfo = userMapper.selectAccountInfoByThirdPartyInfo(thirdPartyInfo);
        // registe
        if (null == accountInfo) {
            long userId = registerService.registerByThirdPartyId(thirdPartyInfo);
            accountInfo = new AccountInfo();
            accountInfo.setId(userId);
            thirdPartyInfo.setUserId(userId);
        }
        return this.login(accountInfo, deviceInfo);//生成token
    }
    
    public void bindThirdPartInfo(ThirdPartyInfo thirdPartyInfo, long userId){
    	if (null == thirdPartyInfo || (StringUtils.isEmpty(thirdPartyInfo.getThirdPartyId()))) {
            throw new CommonException(ReturnCode.THIRD_PARTY_ID_IS_NULL);
        }
        AccountInfo accountInfo = userMapper.selectAccountInfoByThirdPartyInfo(thirdPartyInfo);//绑定该三方的用户
        // registe
        if (null == accountInfo) {//没绑定过的三方
             thirdPartyInfo.setUserId(userId);
             userMapper.insertOrUpdateThirdPartyInfo(thirdPartyInfo);
        }else if(accountInfo.getId() != userId){//已用第三方登陆过,则更新第三方信息对应的userId
        	 
        	
        	userMapper.updateThirdPartUserId(thirdPartyInfo.getThirdPartyId(), thirdPartyInfo.getSource(), userId);
        	//Todo   删除最开始第三方登录时注册的用户信息
        }
//        deviceInfo.setUserId(userId);
//        userMapper.insertOrUpdateDeviceInfo(deviceInfo);
    }
    @Override
    public void logout(String token) {
        userService.removeToken(token);
    }

    private AccountInfo login(AccountInfo accountInfo, DeviceInfo deviceInfo) {
        long userId = accountInfo.getId();
        String newToken = UUID.randomUUID().toString();
        this.changeToken(userId, newToken, accountInfo.getToken());
        accountInfo.setToken(newToken);
        userMapper.updateAccountInfo(accountInfo);
        if (StringUtils.isNotEmpty(deviceInfo.getDeviceId())) {
            deviceInfo.setUserId(userId);
            userMapper.insertOrUpdateDeviceInfo(deviceInfo);
        }
        return accountInfo;
    }

    private void changeToken(long userId, String newToken, String oldToken) {
        if (StringUtils.isNotEmpty(newToken)) {
            userService.saveUserId(userId, newToken);
        }
        if (StringUtils.isNotEmpty(oldToken)) {
            userService.removeToken(oldToken);
        }
    }
    
    
    public ThirdPartyInfo loadUserIdByThirdPartInfo(String thirdPartyId, int source){
    	ThirdPartyInfo thirdPartyInfo=userMapper.loadUserIdByThirdPartInfo(thirdPartyId,source);
    	if(thirdPartyInfo == null){
    		ThirdPartyInfo tempThirdPartyInfo=new ThirdPartyInfo();
    		tempThirdPartyInfo.setThirdPartyId(thirdPartyId);
    		tempThirdPartyInfo.setSource(source);
    		userMapper.insertOrUpdateThirdPartyInfo(tempThirdPartyInfo);
    		return tempThirdPartyInfo;
    	}
    	else
    		//
    		return thirdPartyInfo;
    }
    
    public ThirdPartyInfo checkThirdParty(String thirdPartyId, int source){
    	return userMapper.checkThirdParty(thirdPartyId,source);
    }
}
