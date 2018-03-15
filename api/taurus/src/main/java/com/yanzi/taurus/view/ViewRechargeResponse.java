package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.RechargeInfo;

public class ViewRechargeResponse extends ViewResponseBase{
	private RechargeInfo rechargeInfo;

	public RechargeInfo getRechargeInfo() {
		return rechargeInfo;
	}

	public void setRechargeInfo(RechargeInfo rechargeInfo) {
		this.rechargeInfo = rechargeInfo;
	}
}
