package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserCollegeStatus;
import com.yanzi.pisces.entity.UserCourseTermStatus;

public class ViewUserLoadCourseStatusResponse extends ViewResponseBase {
    private UserCourseTermStatus userCourseTermStatus;
    private UserCollegeStatus userCollegeStatus;

    public UserCollegeStatus getUserCollegeStatus() {
        return userCollegeStatus;
    }

    public void setUserCollegeStatus(UserCollegeStatus userCollegeStatus) {
        this.userCollegeStatus = userCollegeStatus;
    }

    public UserCourseTermStatus getUserCourseTermStatus() {
        return userCourseTermStatus;
    }

    public void setUserCourseTermStatus(UserCourseTermStatus userCourseTermStatus) {
        this.userCourseTermStatus = userCourseTermStatus;
    }
}
