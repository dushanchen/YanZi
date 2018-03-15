package com.yanzi.cancer.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class DialogAuthor {
    private long id;
    private long dialogId;
    private long userId;
    @JSONField(serialize = false)
    private int valid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
