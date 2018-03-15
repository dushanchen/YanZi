package com.yanzi.taurus.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserFriendMapper {
    public void addFriend(@Param(value = "userId") long userId,
            @Param(value = "friendIds") List<Long> friendIds);

    public void removeFriend(@Param(value = "userId") long userId,
            @Param(value = "friendIds") List<Long> friendIds);
}
