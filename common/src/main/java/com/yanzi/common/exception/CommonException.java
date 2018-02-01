package com.yanzi.common.exception;

import com.yanzi.common.constants.ReturnCode;

public class CommonException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 4839184931719368444L;

    private ReturnCode returnCode;

    public CommonException(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String getMessage() {
        return returnCode.getMessage();
    }

    public int getCode() {
        return returnCode.getCode();
    }

}
