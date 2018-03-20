package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class AnswerStyle2 extends StyleBase {
    private String text;
    private String image;
    private long correctId;

    public AnswerStyle2(QuestionTextInfo textInfo) {
        super(textInfo);
        this.image = textInfo.getImage();
        this.text = textInfo.getText();
        this.correctId = textInfo.getCorrectId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getCorrectId() {
        return correctId;
    }

    public void setCorrectId(long correctId) {
        this.correctId = correctId;
    }
}
