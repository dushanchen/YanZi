package com.yanzi.taurus.controller.params;

public class LoadUserPersonalCenterParam {
    private String token;

    private long userId;

    private boolean withBasicInfo = true;

    private boolean withCourseInfo = true;

    private boolean withAppDuration = true;

    private boolean withFriendInfo = false;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isWithBasicInfo() {
        return withBasicInfo;
    }

    public void setWithBasicInfo(boolean withBasicInfo) {
        this.withBasicInfo = withBasicInfo;
    }

    public boolean isWithCourseInfo() {
        return withCourseInfo;
    }

    public void setWithCourseInfo(boolean withCourseInfo) {
        this.withCourseInfo = withCourseInfo;
    }

    public boolean isWithFriendInfo() {
        return withFriendInfo;
    }

    public void setWithFriendInfo(boolean withFriendInfo) {
        this.withFriendInfo = withFriendInfo;
    }

    public boolean isWithAppDuration() {
        return withAppDuration;
    }

    public void setWithAppDuration(boolean withAppDuration) {
        this.withAppDuration = withAppDuration;
    }
}
