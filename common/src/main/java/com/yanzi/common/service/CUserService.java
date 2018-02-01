package com.yanzi.common.service;

import java.util.List;

import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.common.entity.user.UserInfo;

public interface CUserService {

    /**
     * 保存用户token
     * 
     * @param userId
     * @param token
     */
    public void saveUserId(long userId, String token);

    /**
     * 删除用户token
     * 
     * @param token
     */
    public void removeToken(String token);

    /**
     * 根据token获取userId
     * 
     * @param token
     * 
     * @return userId
     */
    public long loadUserId(String token);

    /**
     * 根据token获取userId,无缓存
     * 
     * @param token
     * 
     * @return userId
     */
    long loadUserIdNoCache(String token);

    /**
     * 保存用户基本信息
     * 
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo);

    /**
     * 保存用户Id
     * 
     * @param userId
     */
    public void addUserId(long userId);

    /**
     * 获取用户基本信息
     * 
     * @param userId
     * @return
     */
    public UserInfo loadUserInfo(long userId);

    /**
     * 根据用户id获取用户的基本信息
     * 
     * @param userIds
     * @return
     */
    public List<UserInfo> loadUserInfo(List<Long> userIds);

    /**
     * 获取用户在app中的驻留时长
     * 
     * @param userId
     * 
     * @return time
     */
    public long loadAppDuration(long userId);

    /**
     * 增加用户在app中驻留的时长
     * 
     * @param userId
     * @param durationTime
     */
    public void addAppDuration(long userId, long durationTime);

    /**
     * 修改用户权限
     * 
     * @param permission
     */
    public void savePermissionInfo(PermissionInfo permission);

    /**
     * 获取用户权限
     * 
     * @param userId
     */
    public PermissionInfo loadPermissionInfo(long userId);

    /**
     * 修改用户推送信息
     * 
     * @param pushInfo
     */
    public void savePushInfo(PushInfo pushInfo);

    /**
     * 获取用户推送信息
     * 
     * @param userId
     * @return
     */
    public PushInfo loadPushInfo(long userId);

}
