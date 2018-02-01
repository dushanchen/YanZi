package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;

public class UserLoadCourseStatusParams extends UserActionParamsBase {
    @NotNull
    private long courseId;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
