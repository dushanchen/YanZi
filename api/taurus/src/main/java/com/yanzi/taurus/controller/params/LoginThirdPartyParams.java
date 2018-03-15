package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;

public class LoginThirdPartyParams extends LoginParamsBase {
    @NotEmpty
    private String param;

    private String token;
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    

}
