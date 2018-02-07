package com.yanzi.taurus.controller.params;

public class LoadUserPersonalCenterParam {
    private String token;

    private long userId;

    private boolean withBasicInfo = true;

    private boolean withDialogInfo = true;

    private boolean withCurriculumInfo = true;

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

    public boolean isWithDialogInfo() {
        return withDialogInfo;
    }

    public void setWithDialogInfo(boolean withDialogInfo) {
        this.withDialogInfo = withDialogInfo;
    }

    public boolean isWithCurriculumInfo() {
        return withCurriculumInfo;
    }

    public void setWithCurriculumInfo(boolean withCurriculumInfo) {
        this.withCurriculumInfo = withCurriculumInfo;
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
