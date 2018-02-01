package com.yanzi.cancer.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class DialogInfo {
    private long id;
    private String title;
    private String brief;
    private String image;
    private long likeCount;
    private long commentCount;
    private long godCommentCount;
    @JSONField(serialize = false)
    private int valid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getGodCommentCount() {
        return godCommentCount;
    }

    public void setGodCommentCount(long godCommentCount) {
        this.godCommentCount = godCommentCount;
    }
}
