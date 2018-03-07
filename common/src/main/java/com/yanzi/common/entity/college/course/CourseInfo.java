package com.yanzi.common.entity.college.course;

public class CourseInfo {
    public static final CourseInfo DEFAULT = new CourseInfo();
    
    private long id;
    private String title;
    private String enTitle;
    private String image;
    private int valid;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
