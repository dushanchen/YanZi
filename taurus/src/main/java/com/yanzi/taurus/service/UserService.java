package com.yanzi.taurus.service;

import java.util.List;

import com.yanzi.common.service.CUserService;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface UserService extends CUserService{

    long resetPassword(String phoneNo, String password, String userVerifiCode);

    void modifyPhoneNo(long userId, String phoneNo, String userVerifiCode);

    void modifyPassword(long userId, String newPassword, String oldPassword);

    void addUserThirdPartyInfo(long userId, ThirdPartyInfo thirdPartyInfo);

    void bindingPhoneNo(long userId, String phoneNo, String userVerifiCode, String password);

    AccountInfo getAccountInfoByUserId(long userId);

    List<ThirdPartyInfo> getThirdPartyInfoByUserId(long userId);

}
