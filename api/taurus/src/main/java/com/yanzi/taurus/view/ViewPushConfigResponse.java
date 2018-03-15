package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.PushInfo;

public class ViewPushConfigResponse extends ViewResponseBase {

    private PushInfo pushConfig;

    public ViewPushConfigResponse(PushInfo pushConfig) {
        this.pushConfig = pushConfig;
    }

    public PushInfo getPushConfig() {
        return pushConfig;
    }

    public void setPushConfig(PushInfo pushConfig) {
        this.pushConfig = pushConfig;
    }
}
