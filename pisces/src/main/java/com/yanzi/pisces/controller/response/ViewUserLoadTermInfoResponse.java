package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserTermInfo;

public class ViewUserLoadTermInfoResponse extends ViewResponseBase {
    private UserTermInfo termInfo;

    public UserTermInfo getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(UserTermInfo termInfo) {
        this.termInfo = termInfo;
    }
}
