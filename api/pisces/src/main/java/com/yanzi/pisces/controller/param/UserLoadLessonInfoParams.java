package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;

public class UserLoadLessonInfoParams extends UserActionParamsBase {
    @NotNull
    private long courseId;
    @NotNull
    private long lessonId;

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
