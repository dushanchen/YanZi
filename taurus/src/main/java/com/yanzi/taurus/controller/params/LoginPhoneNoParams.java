package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;
import com.yanzi.taurus.enums.UserSource;

public class LoginPhoneNoParams extends LoginParamsBase {
    @NotEmpty
    private String phoneNo;
    @NotEmpty
    private String password;
    private int source = UserSource.PHONE_NO.getSource();

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

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }
}
