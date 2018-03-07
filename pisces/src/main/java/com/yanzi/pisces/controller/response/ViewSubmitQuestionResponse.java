package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewSubmitQuestionResponse extends ViewResponseBase {
	private long newExp;
	private boolean courseTermDayIsComplete;
	private boolean loadCourseTermWeekCompleteStatus;
	
	public boolean isLoadCourseTermWeekCompleteStatus() {
		return loadCourseTermWeekCompleteStatus;
	}

	public void setLoadCourseTermWeekCompleteStatus(boolean loadCourseTermWeekCompleteStatus) {
		this.loadCourseTermWeekCompleteStatus = loadCourseTermWeekCompleteStatus;
	}

	public boolean isCourseTermDayIsComplete() {
		return courseTermDayIsComplete;
	}

	public void setCourseTermDayIsComplete(boolean courseTermDayIsComplete) {
		this.courseTermDayIsComplete = courseTermDayIsComplete;
	}

	public long getNewExp() {
		return newExp;
	}

	public void setNewExp(long newExp) {
		this.newExp = newExp;
	}


	

}
