package com.yanzi.taurus.entity;

public class AccountInfo {
//    public AccountInfo(){
//    	this.id=0;
//    	this.phoneNo="未绑定";
//    	this.password="未设置";
//    	this.token="未生成";
//    }
	public static final AccountInfo DEFAULT = new AccountInfo();
    
    private long id = 0;
    private String phoneNo = "";
    private String password = "";
    private String token = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
