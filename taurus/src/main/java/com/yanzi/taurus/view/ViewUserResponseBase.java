package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewUserResponseBase extends ViewResponseBase {
    private long id;

    public ViewUserResponseBase(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
