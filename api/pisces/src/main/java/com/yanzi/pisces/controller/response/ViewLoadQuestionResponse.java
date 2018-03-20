package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.question.QuestionInfo;

public class ViewLoadQuestionResponse extends ViewResponseBase{
    private QuestionInfo question;

    public QuestionInfo getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInfo question) {
        this.question = question;
    }
}
