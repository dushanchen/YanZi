package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.pisces.entity.UserLessonInfo;

public class ViewUserLoadLessonInfoResponse extends ViewResponseBase {
    private UserLessonInfo lessonInfo;

    public UserLessonInfo getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(UserLessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }
}
