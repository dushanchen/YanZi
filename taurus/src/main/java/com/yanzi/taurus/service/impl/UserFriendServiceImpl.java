package com.yanzi.taurus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.service.impl.CUserFriendServiceImpl;
import com.yanzi.taurus.mysql.UserFriendMapper;
import com.yanzi.taurus.service.UserFriendService;

@Service
public class UserFriendServiceImpl extends CUserFriendServiceImpl implements UserFriendService {

    @Autowired
    private UserFriendMapper userFriendMapper;

    public void addFriend(long userId, List<Long> friendIds) {
        userFriendMapper.addFriend(userId, friendIds);
        super.addFriend(userId, friendIds);
    }

    public void removeFriend(long userId, List<Long> friendIds) {
        userFriendMapper.removeFriend(userId, friendIds);
        super.removeFriend(userId, friendIds);
    }
}
