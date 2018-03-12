package com.yanzi.common.entity.term;

public class TermLesson {

    public static final TermLesson DEFAULT = new TermLesson();

    private long termId;
    private long lessonId;
    private long startTime;

   

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

}
