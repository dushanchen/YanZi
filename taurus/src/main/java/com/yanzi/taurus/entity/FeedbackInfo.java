package com.yanzi.taurus.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class FeedbackInfo {
    @JSONField(serialize = false)
    private long userId;
    private String message;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
