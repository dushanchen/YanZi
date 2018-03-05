package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;

public class UserLoadTermInfoParams extends UserActionParamsBase {
    @NotNull
    private long termId;

    private long courseId;
    
    private long lessonId;
    
    private long beatCount;
    
    public long getBeatCount() {
		return beatCount;
	}

	public void setBeatCount(long beatCount) {
		this.beatCount = beatCount;
	}

	private long price;
    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getLessonId() {
		return lessonId;
	}

	public void setLessonId(long lessonId) {
		this.lessonId = lessonId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
    
    
}
