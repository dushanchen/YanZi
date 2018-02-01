package com.yanzi.cancer.controller.params;

import com.yanzi.gemini.controller.validator.NotNull;

public class LoadUserLikeDialogParams {
    @NotNull
    private long userId;
    private int pageId = 1;
    private int limit = 10;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
