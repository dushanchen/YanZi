package com.yanzi.common.entity.user;

public class PushInfo {
    public static final PushInfo DEFAULT = new PushInfo();

    private long userId;

    private int study;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStudy() {
        return study;
    }

    public void setStudy(int study) {
        this.study = study;
    }
}
