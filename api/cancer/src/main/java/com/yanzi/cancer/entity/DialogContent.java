package com.yanzi.cancer.entity;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.annotation.JSONField;

public class DialogContent {
    private long id;
    @JSONField(serialize = false)
    private long dialogId;
    private long userId;
    private int type;
    private String text;
    private String smallImage;
    private String bigImage;
    @JSONField(serialize = false)
    private long commentCount;
    private int valid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        try {
            this.text = new String(Base64.decodeBase64(text), "utf-8");
        } catch (Exception e) {
            this.text = new String(Base64.decodeBase64(text));
        }
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }
}
