package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotEmpty;

public class ModifyPasswordParams extends UserActionParamsBase {
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
