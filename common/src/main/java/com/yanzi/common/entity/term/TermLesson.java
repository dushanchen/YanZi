package com.yanzi.common.entity.term;

public class TermLesson {

    public static final TermLesson DEFAULT = new TermLesson();

    private long id;
    private long termId;
	private long lessonId;
    private long startTime;
    private boolean isStart=false;

   
    public long getId() {
		return id;
	}

	

	public void setId(long id) {
		this.id = id;
	}

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
    	this.startTime = startTime;
    	long now = System.currentTimeMillis();
        if (now > this.getStartTime()) {
            this.isStart = true;
            }
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

    
    public boolean getIsStart() {
		return isStart;
	}

	public void setIsStart(boolean isStart) {
		this.isStart = isStart;
	}
}
