package com.yanzi.taurus.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.service.CUserService;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface UserService extends CUserService{

    long resetPassword(String phoneNo, String password, String userVerifiCode);

    void modifyPhoneNo(long userId, String phoneNo, String userVerifiCode);

    void modifyPassword(long userId, String newPassword, String oldPassword);

    void addUserThirdPartyInfo(long userId, ThirdPartyInfo thirdPartyInfo);

    void bindingPhoneNo(long userId, String phoneNo, String userVerifiCode, String password);

    AccountInfo getAccountInfoByUserId(long userId);

    List<ThirdPartyInfo> getThirdPartyInfoByUserId(long userId);
    
    public List<FeedbackInfo> loadUserFeedback(String token);

    public void addUserFeedback(long userId,String message);
    
    public List<UserInfo> fetchFriends(long userId,String nickName);
}
