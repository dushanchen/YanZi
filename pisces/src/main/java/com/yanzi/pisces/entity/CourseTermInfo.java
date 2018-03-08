package com.yanzi.pisces.entity;

public class CourseTermInfo {
	private long courseId;
	private long termId;
	private long userId;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getTermId() {
		return termId;
	}
	public void setTermId(long termId) {
		this.termId = termId;
	}
	
	
}
