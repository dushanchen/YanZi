package com.yanzi.common.service;

import java.util.List;

public interface CUserFriendService {
    /**
     * 新增好友
     * 
     * @param userId
     * @param friendId
     */
    public void addFriend(long userId, long friendId);

    /**
     * 新增好友
     * 
     * @param userId
     * @param friendIds
     */
    public void addFriend(long userId, List<Long> friendIds);

    /**
     * 删除好友
     * 
     * @param userId
     * @param friendId
     */
    public void removeFriend(long userId, long friendId);

    /**
     * 删除好友
     * 
     * @param userId
     * @param friendIds
     */
    public void removeFriend(long userId, List<Long> friendIds);

    /**
     * 获取用户好友ID
     * 
     * @param userId
     * @return
     */
    public List<Long> getFriendIds(long userId, long start, long end);

    /**
     * 获取用户 粉丝ID
     * 
     * @param userId
     * @return
     */
    public List<Long> getFansIds(long userId, long start, long end);

    /**
     * 判断是否是用户好友
     * 
     * @param userId
     * @param friendIds
     * @return
     */
    public List<Boolean> judgeIsFriend(long userId, List<Long> friendIds);

    /**
     * 判断是否是用户好友
     * 
     * @param userId
     * @param friendId
     * @return
     */
    public boolean judgeIsFriend(long userId, long friendId);

    /**
     * 获取好友的数量
     * 
     * @param userId
     * @return
     */
    public long getFriendCount(long userId);

    /**
     * 获取fans的数量
     * 
     * @param userId
     * @return
     */
    public long getFansCount(long userId);
}
