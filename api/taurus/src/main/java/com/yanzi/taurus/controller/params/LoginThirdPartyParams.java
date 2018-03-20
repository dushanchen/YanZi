package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.validator.NotEmpty;
import com.yanzi.taurus.enums.UserSource;

public class LoginThirdPartyParams extends LoginParamsBase {
//    @NotEmpty
//    private String param;
    
    private String token;
    
    private String thirdPartyId;
    
    private int source = UserSource.PHONE_NO.getSource();
    
    private String deviceId;
    
    private String nickName = "";
    
    public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int thirdPartyId() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getSource() {
		return source;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}



//	public String getParam() {
//        return param;
//    }
//
//    public void setParam(String param) {
//        this.param = param;
//    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    

}
