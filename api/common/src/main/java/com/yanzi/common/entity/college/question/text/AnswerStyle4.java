package com.yanzi.common.entity.college.question.text;

import java.util.ArrayList;
import java.util.List;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class AnswerStyle4 extends StyleBase{
    private String text;
    private long correctId;

    private List<DialogStyle1> dialogs = new ArrayList<>();
    
    public AnswerStyle4(QuestionTextInfo textInfo) {
        super(textInfo);
        this.text = textInfo.getText();
        this.correctId = textInfo.getCorrectId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCorrectId() {
        return correctId;
    }

    public void setCorrectId(long correctId) {
        this.correctId = correctId;
    }

    public List<DialogStyle1> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<DialogStyle1> dialogs) {
        this.dialogs = dialogs;
    }
}
