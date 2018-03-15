package com.yanzi.common.redis.user;

import java.util.List;

import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.common.entity.user.UserInfo;

public interface CUserRedisDao {
    /**
     * 根据user token 获取 user id
     * 
     * @param token
     * @return
     */
    public long getUserIdByToken(String token);

    /**
     * 保存用户token id映射 key:token, value:id
     * 
     * @param token
     * @param userId
     */
    public void saveUserIdByToken(String token, long userId);

    /**
     * 删除用户token id映射
     * 
     * @param token
     */
    public void delToken(String token);

    /**
     * 获取用户信息
     * 
     * @param userId
     * @return
     */
    public UserInfo getUserInfoById(long userId);

    /**
     * 获取用户信息
     * 
     * @param userIds
     * @return
     */
    public List<UserInfo> getUserInfoListByIds(List<Long> userIds);

    /**
     * 保存用户信息
     * 
     * @param userId
     * @param userInfo
     */
    public void saveUserInfoById(long userId, UserInfo userInfo);

    /**
     * 获取用户在app中的驻留时长
     * 
     * @param userId
     * @return
     */
    public long getAppDuration(long userId);

    /**
     * 修改用户在app中驻留的时长
     * 
     * @param userId
     * @param durationTime
     */
    public void saveAppDuration(long userId, long durationTime);

    /**
     * 保存所有用户ID
     * 
     * @param userId
     */
    public void saveUserId(long userId);

    /**
     * 获取用户ID
     * 
     * @param start
     * @param end
     * @return
     */
    public List<Long> getUserIds(long start, long end);

    /**
     * 获取所有用户ID的size
     * 
     * @return
     */
    public long getUserIdSize();

    public void savePermission(long userId, PermissionInfo permissionInfo);

    public PermissionInfo loadPermission(long userId);

    public void savePushInfo(long userId, PushInfo pushInfo);

    public PushInfo loadPushInfo(long userId);
    /**获取用户相关的课程经验值
     * 
     * @param userId
     * @return
     */
    public long getCourseExpV2(long userId);

    /**
     * 获取课程相关的知识点
     * @param userId
     * @return
     */
	public long getCourseKnowledgeV2(long userId);
	/**
	 * 获取关注总数
	 * @param userId
	 * @return
	 */
	public long getIdolCount(long userId);
	/**
	 * 获取粉丝数
	 */

	public long getFansCount(long userId);
}
