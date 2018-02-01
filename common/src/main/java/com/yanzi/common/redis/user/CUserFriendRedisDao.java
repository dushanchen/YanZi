package com.yanzi.common.redis.user;

import java.util.List;

public interface CUserFriendRedisDao {
    /**
     * 获取用户所有的关注用户ID
     * 
     * @param userId
     * @return
     */
    public List<Long> getIdol(long userId, long start, long end);

    /**
     * 新增关注用户
     * 
     * @param userId
     * @param idolId
     */
    public void addIdol(long userId, long idolId);

    /**
     * 新增关注用户
     * 
     * @param userId
     * @param idolIds
     */
    public void addIdol(long userId, List<Long> idolIds);

    /**
     * 删除关注用户
     * 
     * @param userId
     * @param idolId
     */
    public void removeIdol(long userId, long idolId);

    /**
     * 删除关注用户
     * 
     * @param userId
     * @param idolIds
     */
    public void removeIdol(long userId, List<Long> idolId);

    /**
     * 判断是否是关注用户
     * 
     * @param userId
     * @param idolId
     * @return
     */
    public boolean judgeIsIdol(long userId, long idolId);

    /**
     * 获取idol的数量
     * 
     * @param userId
     * @return
     */
    public long getIdolCount(long userId);

    /**
     * 获取用户的所有粉丝用户ID
     * 
     * @param userId
     * @return
     */
    public List<Long> getFans(long userId, long start, long end);

    /**
     * 新增用户粉丝
     * 
     * @param userId
     * @param fansId
     */
    public void addFans(long userId, long fansId);

    /**
     * 新增用户粉丝
     * 
     * @param userId
     * @param fansIds
     */
    public void addFans(long userId, List<Long> fansIds);

    /**
     * 删除用户粉丝
     * 
     * @param userId
     * @param fansId
     */
    public void removeFans(long userId, long fansId);

    /**
     * 删除用户粉丝
     * 
     * @param userId
     * @param fansIds
     */
    public void removeFans(long userId, List<Long> fansIds);

    /**
     * 判断是否是粉丝
     * 
     */
    public boolean judgeIsFans(long userId, long fansId);

    /**
     * 获取fans的数量
     * 
     * @param userId
     * @return
     */
    public long getFansCount(long userId);
}
