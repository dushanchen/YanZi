package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.validator.NotNull;

public class LoadQuestionParams {
    @NotNull
    private long courseId;
    @NotNull
    private long lessonId;
    private int index = 1;

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}

