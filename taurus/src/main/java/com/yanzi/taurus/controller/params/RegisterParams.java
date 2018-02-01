package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;

public class RegisterParams {
    @NotEmpty
    private String phoneNo;
    @NotEmpty
    private String password;
    @NotEmpty
    private String verifiCode;
    @NotEmpty
    private String nickName;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
