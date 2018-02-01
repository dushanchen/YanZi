package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotEmpty;

public class ModifyPhoneNoParams extends UserActionParamsBase {
    @NotEmpty
    private String phoneNo;
    @NotEmpty
    private String verifiCode;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
