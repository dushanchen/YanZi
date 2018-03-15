package com.yanzi.common.redis.user.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yanzi.common.constants.RedisPrefixCode;
import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.redis.RedisBaseDao;
import com.yanzi.common.redis.user.CUserFriendRedisDao;
import com.yanzi.common.redis.user.CUserRedisDao;
import com.yanzi.common.utils.CollectionParseUtils;

@Service
public class CUserRedisDaoImpl extends RedisBaseDao implements CUserRedisDao {
	
	@Autowired
	private CUserFriendRedisDao cUserFriendRedisDao;
	
    private String getUserTokenToUserIdPrefix() {
        return RedisPrefixCode.USER_TOKEN_TO_USER_ID.getCode();
    }

    @Override
    public void saveUserIdByToken(String token, long id) {
        String key = getUserTokenToUserIdPrefix();
        this.cacheHash(key, token, Long.toString(id));
    }

    @Override
    public void delToken(String token) {
        String key = getUserTokenToUserIdPrefix();
        this.removeElemOfHash(key, token);
    }

    @Override
    public long getUserIdByToken(String token) {
        String userIdStr = null;
        String key = getUserTokenToUserIdPrefix();
        userIdStr = this.getHash(key, token);
        if (StringUtils.isEmpty(userIdStr)) {
            return 0l;
        }
        return Long.parseLong(userIdStr);
    }

    private String getUserIdPrefix() {
        return RedisPrefixCode.USER_ID_LIST.getCode();
    }

    @Override
    public void saveUserId(long userId) {
        String key = getUserIdPrefix();
        cacheRightList(key, Long.toString(userId));
    }

    @Override
    public List<Long> getUserIds(long start, long end) {
        String key = getUserIdPrefix();
        List<String> userIdStrList = getList(key, start, end);
        if (null == userIdStrList || userIdStrList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> userIdList = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(userIdStrList, userIdList, Long.class);
        return userIdList;
    }

    @Override
    public long getUserIdSize() {
        String key = getUserIdPrefix();
        return getListSize(key);
    }

    private String getUserInfoPrefix() {
        return RedisPrefixCode.USER_ID_TO_USER_INFO.getCode();
    }

    @Override
    public UserInfo getUserInfoById(long userId) {
        String key = getUserInfoPrefix();
        String userInfoStr = this.getHash(key, Long.toString(userId));
        if (StringUtils.isEmpty(userInfoStr)) {
            return UserInfo.DEFAULT;
        }
        return JSON.parseObject(userInfoStr, UserInfo.class);
    }

    @Override
    public void saveUserInfoById(long userId, UserInfo userInfo) {
        String key = getUserInfoPrefix();
        this.cacheHash(key, Long.toString(userId), JSON.toJSONString(userInfo));
    }

    @Override
    public List<UserInfo> getUserInfoListByIds(List<Long> userIds) {
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        String key = getUserInfoPrefix();
        List<String> userIdStrs = new ArrayList<>();
        CollectionParseUtils.NumberParseString(userIds, userIdStrs, Long.class);
        List<String> userInfoStrs = this.getHash(key, userIdStrs);
        List<UserInfo> userInfos = new ArrayList<>();
        CollectionParseUtils.JsonStringParseObject(userInfoStrs, userInfos, UserInfo.class);
        return userInfos;
    }

    private String getAppDurationKey() {
        return RedisPrefixCode.USER_ID_TO_APP_DURATION.getCode();
    }

    @Override
    public long getAppDuration(long userId) {
        String durationStr = getHash(getAppDurationKey(), Long.toString(userId));
        if (StringUtils.isEmpty(durationStr)) {
            return 0l;
        }
        return Long.parseLong(durationStr);
    }

    @Override
    public void saveAppDuration(long userId, long durationTime) {
        cacheHash(getAppDurationKey(), Long.toString(userId), Long.toString(durationTime));
    }

    private String getAllCourseExpKey() {
        return RedisPrefixCode.USER_COLLEGE_EXP.getCode();
    }
    /*获取用户课程相关经验值*/
    @Override
    public long getCourseExpV2(long userId) {
        String expStr = getHash(getAllCourseExpKey(), Long.toString(userId));
        if (StringUtils.isEmpty(expStr)) {
            return 0l;
        }
        return Long.parseLong(expStr);
    }
    
    private String getAllCourseKnowledgeKey() {
        return RedisPrefixCode.USER_COLLEGE_KNOWLEDGE.getCode();
    }
    //获取课程相关的知识点
    @Override
    public long getCourseKnowledgeV2(long userId) {
        String knowledgeStr = getHash(getAllCourseKnowledgeKey(), Long.toString(userId));
        if (StringUtils.isEmpty(knowledgeStr)) {
            return 0;
        }
        return Long.parseLong(knowledgeStr);
    }
    // @Override
    // public List<Long> getTags(long userId) {
    // String key = getTagKey();
    // String tagIdsStr = this.getHash(key, Long.toString(userId));
    // if(StringUtils.isEmpty(tagIdsStr)) {
    // return Collections.emptyList();
    // }
    // List<Long> tagIds = new ArrayList<>();
    // JSONArray jsonArray = JSON.parseArray(tagIdsStr);
    // CollectionParseUtils.JsonArrayParseObject(jsonArray, tagIds, Long.class);
    // return tagIds;
    // }
    //
    // @Override
    // public void saveTags(long userId, List<Long> tagIds) {
    // String key = getTagKey();
    // String value = JSON.toJSONString(tagIds);
    // cacheHash(key, Long.toString(userId), value);
    // }

    private String getUserPermissionPrefix() {
        return RedisPrefixCode.USER_ID_TO_USER_PERMISSION.getCode();
    }
  
    @Override
    public void savePermission(long userId, PermissionInfo permissionInfo) {
        String k = getUserPermissionPrefix();
        this.cacheHash(k, Long.toString(userId), JSON.toJSONString(permissionInfo));
    }
    
    @Override
    public PermissionInfo loadPermission(long userId) {
        String k = getUserPermissionPrefix();
        String permissionStr = this.getHash(k, Long.toString(userId));
        if (StringUtils.isEmpty(permissionStr)) {
            return PermissionInfo.DEFAULT;
        }
        return JSON.parseObject(permissionStr, PermissionInfo.class);
    }

    private String getUserPushInfoPrefix() {
        return RedisPrefixCode.USER_ID_TO_PUSH_INFO.getCode();
    }

    @Override
    public void savePushInfo(long userId, PushInfo pushInfo) {
        String k = getUserPushInfoPrefix();
        this.cacheHash(k, Long.toString(userId), JSON.toJSONString(pushInfo));
    }

    @Override
    public PushInfo loadPushInfo(long userId) {
        String k = getUserPushInfoPrefix();
        String pushStr = this.getHash(k, Long.toString(userId));
        if (StringUtils.isEmpty(pushStr)) {
            return PushInfo.DEFAULT;
        }
        return JSON.parseObject(pushStr, PushInfo.class);
    }

    //获取课关注总数
    @Override
    public long getIdolCount(long userId) {
    	return cUserFriendRedisDao.getIdolCount(userId);
    }

    //获取粉丝总数
    @Override
    public long getFansCount(long userId) {
    	return cUserFriendRedisDao.getFansCount(userId);
    }

}
