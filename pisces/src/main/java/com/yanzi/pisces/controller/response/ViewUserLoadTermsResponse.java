package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserTermInfo;

public class ViewUserLoadTermsResponse extends ViewResponseBase {
    private List<UserTermInfo> termInfos;

    public List<UserTermInfo> getTermInfos() {
        return termInfos;
    }

    public void setTermInfos(List<UserTermInfo> termInfos) {
        this.termInfos = termInfos;
    }

}
