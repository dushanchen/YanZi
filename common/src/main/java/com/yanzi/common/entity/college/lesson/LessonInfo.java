package com.yanzi.common.entity.college.lesson;

public class LessonInfo{
    
    public static final LessonInfo DEFAULT = new LessonInfo();
    
    private long id;
    private long courseId;
    private String title;
    private String image;
    private long index;
    private int valid;
    private String summaryContent;
    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getSummaryContent() {
		return summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}
    
}
