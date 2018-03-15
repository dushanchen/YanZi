package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserCourseInfo;

public class ViewUserLoadCoursesResponse extends ViewResponseBase {
    private List<UserCourseInfo> courseInfos;

    public List<UserCourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(List<UserCourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }
}
