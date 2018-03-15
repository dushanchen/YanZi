package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserRank;

public class ViewUserLoadRankResponse extends ViewResponseBase {
    private List<UserRank> userRanks;

    public List<UserRank> getUserRanks() {
        return userRanks;
    }

    public void setUserRanks(List<UserRank> userRanks) {
        this.userRanks = userRanks;
    }
}
