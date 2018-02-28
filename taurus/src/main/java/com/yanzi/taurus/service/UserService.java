package com.yanzi.taurus.service;

import java.util.List;

import com.yanzi.common.entity.user.BillsInfo;
import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.service.CUserService;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.entity.UserCourseInfo;

public interface UserService extends CUserService{

    long resetPassword(String phoneNo, String password, String userVerifiCode);

    void modifyPhoneNo(long userId, String phoneNo, String userVerifiCode);

    void modifyPassword(long userId, String newPassword, String oldPassword);

    void addUserThirdPartyInfo(long userId, ThirdPartyInfo thirdPartyInfo);

    void bindingPhoneNo(long userId, String phoneNo, String userVerifiCode, String password);

    AccountInfo getAccountInfoByUserId(long userId);

    List<ThirdPartyInfo> getThirdPartyInfoByUserId(long userId);
    
    public long getUserIdByToken(String token);
    
    public List<TagInfo> loadUserTag(long userId);
    
    public UserInfo getUserInfoById(long userId);
    
    public UserCourseInfo loadUserCourseInfo(long userId);
    
    public UserCourseInfo loadUserCourseInfo2(long userId);

    public long loadUserAppDuration(long userId);
   
    public long getFriendCount(long userId);
    
    public long getFansCount(long userId);
    
    public List<FeedbackInfo> loadUserFeedback(String token);

    public void addUserFeedback(long userId,String message);
    
    public List<UserInfo> fetchFriends(long userId,String nickName);
    
    public void addUserCoins(long userId,long number);
    
    public void addUserbills(long userId,long number);
    
    public List<BillsInfo> getUserBillsByUserId(long userId,long number);

}
