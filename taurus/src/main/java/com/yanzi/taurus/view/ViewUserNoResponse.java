package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.taurus.entity.ThirdPartySource;

public class ViewUserNoResponse extends ViewResponseBase{
    private long id;
    private String phoneNo;
    private ThirdPartySource thirdPartySource;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

	public ThirdPartySource getThirdPartySource() {
		return thirdPartySource;
	}

	public void setThirdPartySource(ThirdPartySource thirdPartySource) {
		this.thirdPartySource = thirdPartySource;
	}
}
