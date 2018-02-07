package com.yanzi.taurus.entity;

public class LessonBackgroud {
    public static final LessonBackgroud DEFAULT_INSTANSE = new LessonBackgroud();
    
    private long lessonId;

    private String title;
    private String brief;
    private String image;

    private String summaryBrief;
    private String summaryTitle;
    private String summaryImage;
    private String summarySource;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummaryBrief() {
        return summaryBrief;
    }

    public void setSummaryBrief(String summaryBrief) {
        this.summaryBrief = summaryBrief;
    }

    public String getSummaryTitle() {
        return summaryTitle;
    }

    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    public String getSummaryImage() {
        return summaryImage;
    }

    public void setSummaryImage(String summaryImage) {
        this.summaryImage = summaryImage;
    }

    public String getSummarySource() {
        return summarySource;
    }

    public void setSummarySource(String summarySource) {
        this.summarySource = summarySource;
    }
}
