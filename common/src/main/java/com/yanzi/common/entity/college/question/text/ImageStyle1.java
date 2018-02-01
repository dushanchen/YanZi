package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class ImageStyle1 extends StyleBase{
    private String image;
    
    public ImageStyle1(QuestionTextInfo textInfo){
        super(textInfo);
        this.image = textInfo.getImage();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
