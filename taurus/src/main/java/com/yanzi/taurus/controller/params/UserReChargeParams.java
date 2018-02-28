package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class UserReChargeParams extends UserActionParamsBase{
	private long number;
	
	public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
