package com.yanzi.common.controller.response;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yanzi.common.constants.RequestParams;

public class ResponseEntityWrapper extends ResponseBase {
    private String evid;
    private Object message;

    public ResponseEntityWrapper(Object message, int success, int code, String evid) {
        this.message = message;
        this.success = success;
        this.code = code;

        if (StringUtils.isEmpty(evid)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            evid = request.getParameter(RequestParams.EVENT_ID);
        }
        if (StringUtils.isEmpty(evid)) {
            evid = UUID.randomUUID().toString();
        }
        this.evid = evid;
    }

    public ResponseEntityWrapper(Object message, int success, int code) {
        this(message, success, code, null);
    }

    public String getEvid() {
        return evid;
    }

    public void setEvid(String evid) {
        this.evid = evid;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
