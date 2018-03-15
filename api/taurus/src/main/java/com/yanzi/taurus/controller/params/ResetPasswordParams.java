package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;

public class ResetPasswordParams {
    @NotEmpty
    private String phoneNo;
    @NotEmpty
    private String password;
    @NotEmpty
    private String verifiCode;

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

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
