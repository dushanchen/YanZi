package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;

public class FriendRemoveParams extends UserActionParamsBase {
    @NotNull
    private long[] friendIds;

    public long[] getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(long[] friendIds) {
        this.friendIds = friendIds;
    }
}
