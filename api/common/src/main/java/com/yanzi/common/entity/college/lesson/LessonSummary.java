package com.yanzi.common.entity.college.lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonSummary {

    public static final LessonSummary DEFAULT = new LessonSummary();

    private long lessonId;
    private String image;
    private List<Summary> summaries = new ArrayList<>();

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

    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }

}
