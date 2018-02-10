package com.yanzi.taurus.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.DeviceInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface UserMapper {

    public void insertAccountInfo(@Param(value = "accountInfo") AccountInfo accountInfo);
    
    public void savePassword(@Param(value="userId") long userId,@Param(value="password") String password);

    public void insertOrUpdateUserInfo(@Param(value = "userInfo") UserInfo userInfo);

    public void insertOrUpdatePermissionInfo(
            @Param(value = "permissionInfo") PermissionInfo permissionInfo);

    public void insertOrUpdateDeviceInfo(@Param(value = "deviceInfo") DeviceInfo deviceInfo);

    public void insertOrUpdateThirdPartyInfo(
            @Param(value = "thirdPartyInfo") ThirdPartyInfo thirdPartyInfo);

    // TODO
    public void insertOrUpdatePushInfo(@Param(value = "pushInfo") PushInfo pushInfo);

    public void updateAccountInfo(@Param(value = "accountInfo") AccountInfo accountInfo);

    public AccountInfo selectAccountInfoByPhoneNo(@Param(value = "phoneNo") String phoneNo);

    public AccountInfo selectAccountInfoByUserId(@Param(value = "userId") long userId);

    public AccountInfo selectAccountInfoByThirdPartyInfo(
            @Param(value = "thirdPartyInfo") ThirdPartyInfo thirdPartyInfo);

    public List<ThirdPartyInfo> selectThirdPartyInfoByUserId(@Param(value = "userId") long userId);

    public List<FeedbackInfo> selectFeedbackByUserId(@Param(value = "userId") long userId);
    
    public void addFeedback(@Param("userId") long userId,@Param("message") String message);
    
    public List<UserInfo> fetchFriends(@Param("userId") long userId,@Param("nickName") String nickName);
    
    public void updateThirdPartUserId(@Param("thirdPartyId") String thirdPartyId,@Param("source") int source,@Param("userId") long userId);
}
