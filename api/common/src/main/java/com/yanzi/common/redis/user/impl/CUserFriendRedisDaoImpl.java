package com.yanzi.common.redis.user.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.yanzi.common.constants.RedisPrefixCode;
import com.yanzi.common.redis.RedisBaseDao;
import com.yanzi.common.redis.user.CUserFriendRedisDao;
import com.yanzi.common.utils.CollectionParseUtils;

@Service
public class CUserFriendRedisDaoImpl extends RedisBaseDao implements CUserFriendRedisDao {

    private String getIdolPrefixSet(long userId) {
        return String.format("%s_%sS", RedisPrefixCode.USER_ID_TO_IDOL.getCode(), userId);
    }

    private String getIdolPrefixList(long userId) {
        return String.format("%s_%sL", RedisPrefixCode.USER_ID_TO_IDOL.getCode(), userId);
    }

    @Override
    public List<Long> getIdol(long userId, long start, long end) {
        String listKey = getIdolPrefixList(userId);
        List<String> idolIdStrs = getList(listKey, start, end);
        List<Long> result = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(idolIdStrs, result, Long.class);
        return result;
    }

    @Override
    public void addIdol(long userId, long idolId) {
        String setKey = getIdolPrefixSet(userId);
        String idolIdStr = Long.toString(idolId);
        String listKey = getIdolPrefixList(userId);
        if (!containsSetValue(setKey, idolIdStr)) {
            cacheRightList(listKey, idolIdStr);
            cacheSet(setKey, idolIdStr);
        }
    }

    @Override
    public void addIdol(long userId, List<Long> idolIds) {
        if (null == idolIds) {
            return;
        }
        String setKey = getIdolPrefixSet(userId);
        String listKey = getIdolPrefixList(userId);
        Set<String> idolIdStrSet = new HashSet<>();
        List<String> idolIdStrList = new ArrayList<>();
        for (long idolId : idolIds) {
            String idolIdStr = Long.toString(idolId);
            if (!containsSetValue(setKey, idolIdStr)) {
                idolIdStrSet.add(idolIdStr);
                idolIdStrList.add(idolIdStr);
            }
        }
        cacheSet(setKey, idolIdStrSet);
        cacheRightList(listKey, idolIdStrList);
    }

    @Override
    public void removeIdol(long userId, long idolId) {
        String setKey = getIdolPrefixSet(userId);
        removeElemOfSet(setKey, Long.toString(idolId));

        String listKey = getIdolPrefixList(userId);
        removeElemOfList(listKey, Long.toString(idolId));
    }

    @Override
    public void removeIdol(long userId, List<Long> idolIds) {
        if (null == idolIds) {
            return;
        }
        String setKey = getIdolPrefixSet(userId);
        Set<String> idolIdStrSet = new HashSet<>();
        CollectionParseUtils.NumberParseString(idolIds, idolIdStrSet, Long.class);
        removeElemOfSet(setKey, idolIdStrSet);

        String listKey = getIdolPrefixList(userId);
        for (long idolId : idolIds) {
            removeElemOfList(listKey, Long.toString(idolId));
        }
    }

    @Override
    public boolean judgeIsIdol(long userId, long idolId) {
        String key = getIdolPrefixSet(userId);
        return this.containsSetValue(key, Long.toString(idolId));
    }

    @Override
    public long getIdolCount(long userId) {
        String key = getIdolPrefixSet(userId);
        return this.getSetSize(key);
    }

    private String getFansPrefixSet(long userId) {
        return String.format("%s_%sS", RedisPrefixCode.USER_ID_TO_FANS.getCode(), userId);
    }

    private String getFansPrefixList(long userId) {
        return String.format("%s_%sL", RedisPrefixCode.USER_ID_TO_FANS.getCode(), userId);
    }

    @Override
    public List<Long> getFans(long userId, long start, long end) {
        String listKey = getFansPrefixList(userId);
        List<String> fansIdStrs = getList(listKey, start, end);
        List<Long> result = new ArrayList<Long>();
        CollectionParseUtils.StringParseNumber(fansIdStrs, result, Long.class);
        return result;
    }

    @Override
    public void addFans(long userId, long fansId) {
        String setKey = getFansPrefixSet(userId);
        String fansIdStr = Long.toString(fansId);
        String listKey = getFansPrefixList(userId);
        if (!containsSetValue(setKey, fansIdStr)) {
            cacheRightList(listKey, fansIdStr);
            cacheSet(setKey, fansIdStr);
        }
    }

    @Override
    public void addFans(long userId, List<Long> fansIds) {
        if (null == fansIds) {
            return;
        }
        String setKey = getFansPrefixSet(userId);
        String listKey = getFansPrefixList(userId);
        Set<String> fansIdStrSet = new HashSet<>();
        List<String> fansIdStrList = new ArrayList<>();
        for (long fansId : fansIds) {
            String fansIdStr = Long.toString(fansId);
            if (!containsSetValue(setKey, fansIdStr)) {
                fansIdStrSet.add(fansIdStr);
                fansIdStrList.add(fansIdStr);
            }
        }
        cacheSet(setKey, fansIdStrSet);
        cacheRightList(listKey, fansIdStrList);
    }

    @Override
    public void removeFans(long userId, long fansId) {
        String setKey = getFansPrefixSet(userId);
        removeElemOfSet(setKey, Long.toString(fansId));

        String listKey = getFansPrefixList(userId);
        cacheRightList(listKey, Long.toString(fansId));
    }

    @Override
    public void removeFans(long userId, List<Long> fansIds) {
        if (null == fansIds) {
            return;
        }
        String setKey = getFansPrefixSet(userId);
        Set<String> fansIdStrSet = new HashSet<String>();
        CollectionParseUtils.NumberParseString(fansIds, fansIdStrSet, Long.class);
        removeElemOfSet(setKey, fansIdStrSet);

        String listKey = getFansPrefixList(userId);
        for (long fansId : fansIds) {
            cacheRightList(listKey, Long.toString(fansId));
        }
    }

    @Override
    public boolean judgeIsFans(long userId, long fansId) {
        String setKey = getFansPrefixSet(userId);
        return this.containsSetValue(setKey, Long.toString(fansId));
    }

    @Override
    public long getFansCount(long userId) {
        String setKey = getFansPrefixSet(userId);
        return this.getSetSize(setKey);
    }
}
