package com.yanzi.common.entity.user;

public class PermissionInfo {
    public static PermissionInfo DEFAULT = new PermissionInfo();

    private long userId;
    
    static {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    };
}
