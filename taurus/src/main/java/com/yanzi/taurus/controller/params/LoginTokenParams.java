package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;

public class LoginTokenParams extends LoginParamsBase{
    @NotEmpty
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
