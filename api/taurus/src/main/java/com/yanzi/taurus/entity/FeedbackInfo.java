package com.yanzi.taurus.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class FeedbackInfo {
    @JSONField(serialize = false)
    private long userId;
    private String message;
    private Date addTime;
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getAddTime() {
		String df = "yyyy年MM月dd日 hh:mm:ss";
		SimpleDateFormat sd = new SimpleDateFormat(df);
		return sd.format(addTime);
		  
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
}
