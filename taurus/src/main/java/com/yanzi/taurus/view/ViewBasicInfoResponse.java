package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.UserInfo;


public class ViewBasicInfoResponse extends ViewResponseBase {
    private UserInfo basicInfo;

    private PermissionInfo permissionInfo;

    public UserInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(UserInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public PermissionInfo getPermissionInfo() {
        return permissionInfo;
    }

    public void setPermissionInfo(PermissionInfo permissionInfo) {
        this.permissionInfo = permissionInfo;
    }
}
