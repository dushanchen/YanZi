package com.yanzi.common.controller.response;

public abstract class ResponseBase {
	protected int success;
	protected int code;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
