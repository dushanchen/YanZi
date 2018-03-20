package com.yanzi.common.entity.college.question.text;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class StyleBase {
    protected long id;
    protected int type;
    
    public StyleBase(QuestionTextInfo textInfo){
        this.id = textInfo.getId();
        this.type = textInfo.getType();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
