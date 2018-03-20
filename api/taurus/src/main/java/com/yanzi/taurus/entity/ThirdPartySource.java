package com.yanzi.taurus.entity;

public class ThirdPartySource {

	private boolean phoneNo = false;
	private boolean whchat = false;
	private boolean qq = false;
	private boolean sina_blog = false;
	
	
	public ThirdPartySource() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ThirdPartySource(boolean phoneNo, boolean whchat, boolean qq,
			boolean sina_blog) {
		super();
		this.phoneNo = phoneNo;
		this.whchat = whchat;
		this.qq = qq;
		this.sina_blog = sina_blog;
	}


	public boolean isPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(boolean phoneNo) {
		this.phoneNo = phoneNo;
	}


	public boolean isWhchat() {
		return whchat;
	}


	public void setWhchat(boolean whchat) {
		this.whchat = whchat;
	}


	public boolean isQq() {
		return qq;
	}


	public void setQq(boolean qq) {
		this.qq = qq;
	}


	public boolean isSina_blog() {
		return sina_blog;
	}


	public void setSina_blog(boolean sina_blog) {
		this.sina_blog = sina_blog;
	}
	
}
