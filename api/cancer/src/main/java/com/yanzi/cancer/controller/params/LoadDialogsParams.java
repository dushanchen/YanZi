package com.yanzi.cancer.controller.params;

public class LoadDialogsParams {
    private int pageId = 1;
    private int limit = 10;

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
