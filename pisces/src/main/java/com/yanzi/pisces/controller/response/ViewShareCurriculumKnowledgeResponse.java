package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.pisces.entity.LessonBackgroud;
public class ViewShareCurriculumKnowledgeResponse extends ViewResponseBase {
    private long knowledge;
//    private long sustainedCompleteDayCount;
    private UserInfo userInfo;
    private LessonPrimer lesson;
//    private LessonBackgroud lessonBackgroud;

    public long getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(long knowledge) {
        this.knowledge = knowledge;
    }
 
    public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

    public LessonPrimer getLesson() {
		return lesson;
	}

	public void setLesson(LessonPrimer lesson) {
		this.lesson = lesson;
	}

//	public LessonBackgroud getLessonBackgroud() {
//        return lessonBackgroud;
//    }
//
//    public void setLessonBackgroud(LessonBackgroud lessonBackgroud) {
//        this.lessonBackgroud = lessonBackgroud;
//    }
//
//    public long getSustainedCompleteDayCount() {
//        return sustainedCompleteDayCount;
//    }
//
//    public void setSustainedCompleteDayCount(long sustainedCompleteDayCount) {
//        this.sustainedCompleteDayCount = sustainedCompleteDayCount;
//    }
}
