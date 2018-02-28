package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.BillsInfo;

public class ViewBillResponse extends ViewResponseBase{
	private List<BillsInfo> BillsInfo;
	
	public List<BillsInfo> getUserBill() {
        return BillsInfo;
    }

    public void setUserBill(List<BillsInfo> BillsInfo) {
        this.BillsInfo = BillsInfo;
    }

}
