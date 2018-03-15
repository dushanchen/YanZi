package com.yanzi.common.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.redis.user.CUserFriendRedisDao;
import com.yanzi.common.service.CUserFriendService;

@Service("cUserFriendService")
public class CUserFriendServiceImpl implements CUserFriendService {

    @Autowired
    private CUserFriendRedisDao cUserFriendRedisDao;

    @Override
    public void addFriend(long userId, long friendId) {
        cUserFriendRedisDao.addIdol(userId, friendId);
        cUserFriendRedisDao.addFans(friendId, userId);
    }

    @Override
    public void addFriend(long userId, List<Long> friendIds) {
        if (null == friendIds || friendIds.isEmpty()) {
            return;
        }
        cUserFriendRedisDao.addIdol(userId, friendIds);
        for (long friendId : friendIds) {
            cUserFriendRedisDao.addFans(friendId, userId);
        }
    }

    @Override
    public void removeFriend(long userId, long friendId) {
        cUserFriendRedisDao.removeIdol(userId, friendId);
        cUserFriendRedisDao.removeFans(friendId, userId);
    }

    @Override
    public void removeFriend(long userId, List<Long> friendIds) {
        if (null == friendIds || friendIds.isEmpty()) {
            return;
        }
        cUserFriendRedisDao.removeIdol(userId, friendIds);
        for (long friendId : friendIds) {
            cUserFriendRedisDao.removeFans(friendId, userId);
        }
    }

    @Override
    public List<Long> getFriendIds(long userId, long start, long end) {
        return cUserFriendRedisDao.getIdol(userId, start, end);
    }

    @Override
    public List<Long> getFansIds(long userId, long start, long end) {
        return cUserFriendRedisDao.getFans(userId, start, end);
    }

    @Override
    public List<Boolean> judgeIsFriend(long userId, List<Long> friendIds) {
        if (null == friendIds || friendIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Boolean> result = new ArrayList<>();
        for (long friendId : friendIds) {
            result.add(cUserFriendRedisDao.judgeIsIdol(userId, friendId));
        }
        return result;
    }

    @Override
    public boolean judgeIsFriend(long userId, long friendId) {
        return cUserFriendRedisDao.judgeIsIdol(userId, friendId);
    }

    @Override
    public long getFriendCount(long userId) {
        return cUserFriendRedisDao.getIdolCount(userId);
    }

    @Override
    public long getFansCount(long userId) {
        return cUserFriendRedisDao.getFansCount(userId);
    }
}
