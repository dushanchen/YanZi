package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewCheckPurchaseResponse extends ViewResponseBase{
	private long type;	//错误购买类型
	private String des;	//错误操作类型描述
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
}
