package com.yanzi.common.entity.user;


public class UserInfo{
    public static final UserInfo DEFAULT = new UserInfo();

    private long id;
    private String nickName = "";
    private String icon = "";
    private int sex = 0;
    private String introduce ="";
    private String signature = "";
    private String company = "";
    private String education = "";
    /**
     * 雁币
     */
    private double coins = 0;

    public UserInfo() {
    }

    public UserInfo(long id) {
        this.id = id;
    }

    public UserInfo(long id, String nickName, String icon) {
        this.id = id;
        this.nickName = nickName;
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}
    
}
