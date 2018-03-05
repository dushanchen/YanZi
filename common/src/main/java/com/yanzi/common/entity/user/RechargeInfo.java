package com.yanzi.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;
public class RechargeInfo {
	@JSONField(serialize = false)
	private long userId;
	private boolean state;
	private long number;
	
	 public RechargeInfo(long userId,boolean state,long number) {
	        this.userId = userId;
	        this.state = state;
	        this.number = number;
	    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
}
