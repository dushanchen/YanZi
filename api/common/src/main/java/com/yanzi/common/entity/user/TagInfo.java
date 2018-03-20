package com.yanzi.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;

public class TagInfo {
    public static final TagInfo DEFAULT = new TagInfo();
    
    private long id;
    private String tag;
    @JSONField(serialize = false)
    private int valid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
