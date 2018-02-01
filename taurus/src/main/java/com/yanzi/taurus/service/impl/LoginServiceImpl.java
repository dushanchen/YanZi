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
        }
        return this.login(accountInfo, deviceInfo);
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

}
