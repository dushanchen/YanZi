package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.BillsInfo;

public class ViewUserPurchaseResponse extends ViewResponseBase{
	private BillsInfo billsInfo;

	public BillsInfo getBillsInfo() {
		return billsInfo;
	}

	public void setBillsInfo(BillsInfo billsInfo) {
		this.billsInfo = billsInfo;
	}	

	 
    
}
