package com.yanzi.taurus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PushService;
import com.yanzi.taurus.service.UserService;

@Service
public class PushServiceImpl implements PushService{
    
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void setPushInfo(PushInfo pushInfo) {
        userService.savePushInfo(pushInfo);
//        userMapper.insertOrUpdatePushInfo(pushInfo);
    }
}
