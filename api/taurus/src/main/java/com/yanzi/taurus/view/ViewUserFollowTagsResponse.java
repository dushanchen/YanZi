package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewUserFollowTagsResponse extends ViewResponseBase {
	private String msg = "标签绑定失败";

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
