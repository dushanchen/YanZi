package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class AnswerStyle1 extends StyleBase {
    private String text;
    private String breif;
    private long correctId;

    public AnswerStyle1(QuestionTextInfo textInfo) {
        super(textInfo);
        this.text = textInfo.getText();
        this.breif = textInfo.getBrief();
        this.correctId = textInfo.getCorrectId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBreif() {
        return breif;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public long getCorrectId() {
        return correctId;
    }

    public void setCorrectId(long correctId) {
        this.correctId = correctId;
    }
}
