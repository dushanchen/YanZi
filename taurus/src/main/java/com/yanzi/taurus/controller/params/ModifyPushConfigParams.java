package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class ModifyPushConfigParams extends UserActionParamsBase {
    private int study;

    public int getStudy() {
        return study;
    }

    public void setStudy(int study) {
        this.study = study;
    }
}
