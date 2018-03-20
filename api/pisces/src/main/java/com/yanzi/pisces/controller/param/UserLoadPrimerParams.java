package com.yanzi.pisces.controller.param;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class UserLoadPrimerParams extends UserActionParamsBase{
	private long courseId;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}	
}
