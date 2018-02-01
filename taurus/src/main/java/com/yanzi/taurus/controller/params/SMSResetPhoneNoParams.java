package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class SMSResetPhoneNoParams extends UserActionParamsBase{
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
