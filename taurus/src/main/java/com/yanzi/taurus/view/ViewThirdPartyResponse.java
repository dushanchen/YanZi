package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public class ViewThirdPartyResponse extends ViewResponseBase{
	
	private List<ThirdPartyInfo> thirdPartys;
	public ViewThirdPartyResponse(List<ThirdPartyInfo> thirdPartys) {
        this.thirdPartys = thirdPartys;
    }
	public List<ThirdPartyInfo> getThirdPartys() {
		return thirdPartys;
	}
	public void setThirdPartys(List<ThirdPartyInfo> thirdPartys) {
		this.thirdPartys = thirdPartys;
	}


 
}
