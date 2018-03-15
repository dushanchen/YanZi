package com.yanzi.common.entity.college.level;

import com.alibaba.fastjson.annotation.JSONField;

public class LevelInfo {
    public static final LevelInfo DEFAULT = new LevelInfo();

    private long id;
    private long courseId;
    private int level;
    private String image = "";
    @JSONField(serialize = false)
    private long minExp;
    @JSONField(serialize = false)
    private double coin;
    @JSONField(serialize = false)
    private int valid;

	public long getId() {
        return id;
    }

	public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public long getMinExp() {
        return minExp;
    }

    public void setMinExp(long minExp) {
        this.minExp = minExp;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }
}
