package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class AnswerStyle3 extends StyleBase {
    private String text;
    private String image;

    public AnswerStyle3(QuestionTextInfo textInfo) {
        super(textInfo);
        this.image = textInfo.getImage();
        this.text = textInfo.getText();
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
}
