package com.yanzi.pisces.entity;

import com.yanzi.common.entity.college.course.CourseInfo;

public class UserCourseInfo {
    private CourseInfo courseInfo;

    public UserCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

    public CourseInfo getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(CourseInfo courseInfo) {
        this.courseInfo = courseInfo;
    }

}
