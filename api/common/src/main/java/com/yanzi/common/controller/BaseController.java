package com.yanzi.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.common.constants.LogLevel;
import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.constants.SuccessCode;
import com.yanzi.common.constants.RequestParams.ResponseType;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.trace.MLogger;

public class BaseController<T> {
    private static final Logger logger = new MLogger(BaseController.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> exp(HttpServletRequest request, Exception ex) {
        LogLevel logLevel = LogLevel.WARN;
        SuccessCode successCode = SuccessCode.UNKNOWN_ERROR;
        String message = String.format("Get exception for %s?%s : %s", request.getRequestURI(),
                request.getQueryString(), ex.getMessage());
        int code = 0;
        // 参数为空
        if (ex instanceof BindException) {
            logLevel = LogLevel.WARN;
            successCode = SuccessCode.PARAMS_IS_NULL;
        } else if (ex instanceof CommonException) {
            logLevel = LogLevel.INFO;
            successCode = SuccessCode.SUCCESS;
            code = ((CommonException) ex).getCode();
            message = ex.getMessage();
        }

        switch (logLevel) {
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
            default:
                logger.error(message, ex);
                break;
        }
        ResponseEntityWrapper result = new ResponseEntityWrapper(message, successCode.getCode(), code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(ResponseType.json.toMediaType());
        return new ResponseEntity<ResponseEntityWrapper>(result, headers, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseEntityWrapper> packageSuccessData(T data, int success, int code) {
        ResponseEntityWrapper response = new ResponseEntityWrapper(data, success, code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(ResponseType.json.toMediaType());
        return new ResponseEntity<ResponseEntityWrapper>(response, headers, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseEntityWrapper> packageSuccessData(T data){
        return packageSuccessData(data, SuccessCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getCode());
    }
    
}
