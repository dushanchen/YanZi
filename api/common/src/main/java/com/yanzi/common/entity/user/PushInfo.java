package com.yanzi.common.entity.user;

public class PushInfo {
    public static final PushInfo DEFAULT = new PushInfo();

    private long userId;

    private int status;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
