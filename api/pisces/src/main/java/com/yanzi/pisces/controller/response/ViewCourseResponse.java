package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.course.CourseInfo;


public class ViewCourseResponse extends ViewResponseBase {
    private List<CourseInfo> course;

    public ViewCourseResponse(List<CourseInfo> course) {
        this.course = course;
    }

    public List<CourseInfo> getCurriculums() {
        return course;
    }

    public void setCurriculums(List<CourseInfo> course) {
        this.course = course;
    }

}
