package com.yanzi.taurus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.enums.SMSVerifiCodeType;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PermissionService;
import com.yanzi.taurus.service.PhoneService;
import com.yanzi.taurus.service.RegisterService;
import com.yanzi.taurus.service.SMSService;
import com.yanzi.taurus.service.UserService;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private SMSService smsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PhoneService phoneService;

//    @Override
//    public long registerByPhoneNo(String phoneNo, String password, String userVerifiCode, String nickName) {
//        phoneService.isNotRegisted(phoneNo);
//        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.USER_REGISTER);
//        AccountInfo accountInfo = new AccountInfo();
//        accountInfo.setPhoneNo(phoneNo);
//        accountInfo.setPassword(password);
//        userMapper.insertAccountInfo(accountInfo);
//        register(accountInfo, nickName);
//        return accountInfo.getId();
//    }
    /**
     * 注册第一步，
     * dusc
     */
    @Override
    public long registerByPhoneNo(String phoneNo, String userVerifiCode, String nickName) {
        phoneService.isNotRegisted(phoneNo);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.USER_REGISTER);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setPhoneNo(phoneNo);
        userMapper.insertAccountInfo(accountInfo);
        register(accountInfo, nickName);
        return accountInfo.getId();
    }
    /**
     * 注册第二步，
     * dusc
     */
    @Override
    public void savePassword(long userId, String password) {
        userMapper.savePassword(userId, password);
    }
    @Override
    public long registerByThirdPartyId(ThirdPartyInfo thirdPartyInfo) {
        AccountInfo accountInfo = new AccountInfo();
        //accountInfo.setPhoneNo(thirdPartyInfo.getThirdPartyId());//三方ID首次做为PhoneID入表
        
        userMapper.insertAccountInfo(accountInfo);//生成userId
        long userId=accountInfo.getId();
        thirdPartyInfo.setUserId(userId);
        String thirdPartyId=thirdPartyInfo.getThirdPartyId();
        
        userMapper.UpdateThirdPartyInfoUserId(userId,thirdPartyId);//更新数据库
        
        
        register(accountInfo, thirdPartyInfo.getNickName());
        return accountInfo.getId();
    }

    private void register(AccountInfo accountInfo, String nickName) {
        long userId = accountInfo.getId();
        // add userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setNickName(nickName);
        userService.saveUserInfo(userInfo);
        userService.addUserId(userInfo.getId());//保存redis

        // add permission
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setUserId(userId);
        permissionService.setUserPermission(userId, permissionInfo);
    }
}
