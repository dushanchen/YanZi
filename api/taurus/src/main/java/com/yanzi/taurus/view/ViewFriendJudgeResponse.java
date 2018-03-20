package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewFriendJudgeResponse extends ViewResponseBase {
    private List<Boolean> isFriends;

    public ViewFriendJudgeResponse(List<Boolean> isFriends) {
        this.isFriends = isFriends;
    }

    public List<Boolean> getIsFriends() {
        return isFriends;
    }

    public void setIsFriends(List<Boolean> isFriends) {
        this.isFriends = isFriends;
    }
}
