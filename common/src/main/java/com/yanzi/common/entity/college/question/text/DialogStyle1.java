package com.yanzi.common.entity.college.question.text;

import java.util.ArrayList;
import java.util.List;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class DialogStyle1 extends StyleBase{
    private String text;
    private long userId;

    private List<AnswerStyle4> answers = new ArrayList<>();
    
    public DialogStyle1(QuestionTextInfo textInfo) {
        super(textInfo);
        this.text = textInfo.getText();
        this.userId = textInfo.getUserId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<AnswerStyle4> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerStyle4> answers) {
        this.answers = answers;
    }
}
