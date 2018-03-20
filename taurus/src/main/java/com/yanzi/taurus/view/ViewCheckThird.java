package com.yanzi.taurus.view;


import com.yanzi.taurus.view.ViewLoginResponse;
public class ViewCheckThird extends ViewLoginResponse{

	public ViewCheckThird(long id, String token) {
		super(id, token);
		// TODO Auto-generated constructor stub
	}
	
	private int type;		//错误购买类型 参照ReturnCode.java
	private String des;	//错误操作类型描述 
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
}
