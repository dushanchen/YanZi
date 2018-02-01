package com.yanzi.taurus.service;

import com.yanzi.common.entity.user.PermissionInfo;

public interface PermissionService {
    void setUserPermission(long userId, PermissionInfo permissionInfo);
}
