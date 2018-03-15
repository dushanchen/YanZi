package com.yanzi.taurus.entity;

import java.util.List;

import com.yanzi.common.entity.user.BillsInfo;

public class UserBillsInfo {
	private List<BillsInfo> bill;
	public List<BillsInfo> getBill(){
		return bill;
	}
	
	public void setBill(List<BillsInfo> bill){
		this.bill=bill;
	}
}
