package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;

public class UserReChargeParams extends UserActionParamsBase{
	private double number;
	
	public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}
