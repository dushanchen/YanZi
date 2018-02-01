package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserLessonInfo;

public class ViewUserLoadLessonsResponse extends ViewResponseBase {
    private List<UserLessonInfo> lessonInfos;

    public List<UserLessonInfo> getLessonInfos() {
        return lessonInfos;
    }

    public void setLessonInfos(List<UserLessonInfo> lessonInfos) {
        this.lessonInfos = lessonInfos;
    }
}
