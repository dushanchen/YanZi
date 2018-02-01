package com.yanzi.taurus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PermissionService;
import com.yanzi.taurus.service.UserService;

@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void setUserPermission(long userId, PermissionInfo permissionInfo) {
        userMapper.insertOrUpdatePermissionInfo(permissionInfo);
        userService.savePermissionInfo(permissionInfo);
    }
}
