package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class FollowTagParams extends UserActionParamsBase {
    private long[] tagIds;

    public long[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(long[] tagIds) {
        this.tagIds = tagIds;
    }
}
