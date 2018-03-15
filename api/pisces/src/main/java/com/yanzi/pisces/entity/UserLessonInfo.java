package com.yanzi.pisces.entity;

import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.term.TermLesson;

public class UserLessonInfo {
	
	public static final UserLessonInfo DEFAULT = new UserLessonInfo();
	
	public UserLessonInfo(){
		
	}
	
    private LessonInfo lessonInfo;
    private LessonPrimer lessonPrimer;
    private LessonSummary lessonSummary;
    private TermLesson termLesson;//lessonId->lesson和term对应关系
    private UserLessonStatus userLessonStatus;
    private boolean isStart = false;
    private int questionCount;

    public UserLessonInfo(LessonInfo lessonInfo, TermLesson termLesson,
            UserLessonStatus userLessonStatus) {
        this.lessonInfo = lessonInfo;
        this.termLesson = termLesson;
        this.userLessonStatus = userLessonStatus;
        long now = System.currentTimeMillis();
        if (now > termLesson.getStartTime()) {
            this.isStart = true;
        }
    }

    public UserLessonInfo(LessonInfo lessonInfo,TermLesson termLesson, LessonPrimer lessonPrimer,
            LessonSummary lessonSummary, UserLessonStatus userLessonStatus, int questionCount) {
        this.lessonInfo = lessonInfo;
        this.termLesson = termLesson;
        this.lessonPrimer = lessonPrimer;
        this.lessonSummary = lessonSummary;
        this.questionCount = questionCount;
        this.userLessonStatus = userLessonStatus;
        long now = System.currentTimeMillis();
        if (now > termLesson.getStartTime()) {
            this.isStart = true;
        }
    }

    public LessonInfo getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public TermLesson getTermLesson() {
        return termLesson;
    }

    public void setTermLesson(TermLesson termLesson) {
        this.termLesson = termLesson;
         	
    }

    public UserLessonStatus getUserLessonStatus() {
        return userLessonStatus;
    }

    public void setUserLessonStatus(UserLessonStatus userLessonStatus) {
        this.userLessonStatus = userLessonStatus;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public LessonPrimer getLessonPrimer() {
        return lessonPrimer;
    }

    public void setLessonPrimer(LessonPrimer lessonPrimer) {
        this.lessonPrimer = lessonPrimer;
    }

    public LessonSummary getLessonSummary() {
        return lessonSummary;
    }

    public void setLessonSummary(LessonSummary lessonSummary) {
        this.lessonSummary = lessonSummary;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
