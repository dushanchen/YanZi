package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;

public class UserLoadTermInfoParams extends UserActionParamsBase {
    @NotNull
    private long termId;

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }
}
