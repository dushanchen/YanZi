package com.yanzi.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.redis.user.CUserCollegeRedisDao;
import com.yanzi.common.redis.user.CUserRedisDao;
import com.yanzi.common.service.CUserService;
import com.yanzi.common.service.CourseService;
import com.yanzi.common.trace.MLogger;

@Service("cUserService")
public class CUserServiceImpl implements CUserService, InitializingBean {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(CUserServiceImpl.class);

    @Value("#{configProperties['common.user_token_to_id_cache_concurrency']}")
    private int authorCacheConcurrency = 100;
    @Value("#{configProperties['common.user_token_to_id_cache_size']}")
    private int authorCacheSize = 1000;
    @Value("#{configProperties['common.user_token_to_id_cache_duration']}")
    private int authorCacheDuration = 60;

    private LoadingCache<String, Long> userTokenToIdCache;

    @Autowired
    private CUserRedisDao cUserRedisDao;
    @Autowired
    private CUserCollegeRedisDao cUserCollegeRedisDao;
    @Autowired
    private CourseService courseService;

    @Override
    public void afterPropertiesSet() {//判断本地服务器缓存中是否存在数据，如果没有则去redis服务器取
        userTokenToIdCache = CacheBuilder.newBuilder().concurrencyLevel(authorCacheConcurrency)
                .maximumSize(authorCacheSize)
                .expireAfterWrite(authorCacheDuration, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Long>() {

                    @Override
                    public Long load(String token) throws Exception {
                        return loadUserIdNoCache(token);
                    }
                });
    }

    @Override
    public void saveUserId(long userId, String token) {
        cUserRedisDao.saveUserIdByToken(token, userId);
    }

    @Override
    public long loadUserIdNoCache(String token) {
        long userId = cUserRedisDao.getUserIdByToken(token);
        if (0 == userId) {
            throw new CommonException(ReturnCode.TOKEN_IS_NOT_EXIST);
        }
        return userId;
    }

    @Override
    public long loadUserId(String token) {
        long userId = 0;
        try {
            userId = userTokenToIdCache.get(token);
        } catch (ExecutionException e) {
            userId = this.loadUserIdNoCache(token);
        }
        if (0 == userId) {
            throw new CommonException(ReturnCode.TOKEN_IS_NOT_EXIST);
        }
        return userId;
    }
    @Override
    public List<Long> loadUserSubscribedCourseId(long userId) {
        List<Long> courseIds = cUserCollegeRedisDao.getUserSubscribedCourseV2(userId);
        if (null == courseIds || courseIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> result = new ArrayList<>();
        for (long courseId : courseIds) {
            if (courseService.containsCourse(courseId)) {
                result.add(courseId);
            }
        }
        Collections.sort(result);
        return result;
    }
    
    @Override
    public void removeToken(String token) {
        cUserRedisDao.delToken(token);
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        cUserRedisDao.saveUserInfoById(userInfo.getId(), userInfo);
    }

    @Override
    public UserInfo loadUserInfo(long userId) {
        return cUserRedisDao.getUserInfoById(userId);
    }

    @Override
    public List<UserInfo> loadUserInfo(List<Long> userIds) {
        List<UserInfo> userInfos = cUserRedisDao.getUserInfoListByIds(userIds);
        return userInfos;
    }

    @Override
    public long loadAppDuration(long userId) {
        return cUserRedisDao.getAppDuration(userId);
    }

    @Override
    public void addAppDuration(long userId, long durationTime) {
        long nowTime = cUserRedisDao.getAppDuration(userId);
        nowTime += durationTime;
        cUserRedisDao.saveAppDuration(userId, nowTime);
    }

    @Override
    public void addUserId(long userId) {
        cUserRedisDao.saveUserId(userId);
    }

    @Override
    public void savePermissionInfo(PermissionInfo permission) {
        cUserRedisDao.savePermission(permission.getUserId(), permission);
    }

    @Override
    public PermissionInfo loadPermissionInfo(long userId) {
        return cUserRedisDao.loadPermission(userId);
    }

    @Override
    public void savePushInfo(PushInfo pushInfo) {
        cUserRedisDao.savePushInfo(pushInfo.getUserId(), pushInfo);
    }

    @Override
    public PushInfo loadPushInfo(long userId) {
        return cUserRedisDao.loadPushInfo(userId);
    }

	@Override
	public long getUserCount() {
		return cUserRedisDao.getUserIdSize();
	}

	@Override
	public List<Long> getUserIds(long start, long end) {
		return cUserRedisDao.getUserIds(start, end);
	}
    
	public long getFriendCount(long userId) {
		return cUserRedisDao.getIdolCount(userId);
	}

	@Override
	public long loadUserCourseExp(long userId, long courseId,long termId) {
        return cUserCollegeRedisDao.loadCourseTermExp(userId, courseId, termId);
    }
	
	@Override
	public long loadUserCourseLevel(long userId, long courseId,long termId) {
        return cUserCollegeRedisDao.loadCourseTermLevel(userId, courseId, termId);
    }
}
