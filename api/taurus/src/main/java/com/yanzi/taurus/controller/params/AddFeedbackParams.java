package com.yanzi.taurus.controller.params;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotEmpty;

public class AddFeedbackParams extends UserActionParamsBase{
	@NotEmpty
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
