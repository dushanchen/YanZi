package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class TextStyle extends StyleBase{
    private String text;
    
    public TextStyle(QuestionTextInfo textInfo){
        super(textInfo);
        this.text = textInfo.getText();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
