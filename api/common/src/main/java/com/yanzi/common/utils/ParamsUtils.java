package com.yanzi.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.service.CUserService;

@Component
public class ParamsUtils {
    private static final String USERID_SECRET_KEY = "yanzi@123456";

    @Autowired
    private CUserService cUserService;

    public long getUserId(UserActionParamsBase params) {
//        if (params.getUserId() != 0
//                && StringUtils.equalsIgnoreCase(USERID_SECRET_KEY, params.getSecretKey())) {
//            return params.getUserId();
//        }
        if (params.getUserId() != 0 ) {
            return params.getUserId();
        }
        return cUserService.loadUserId(params.getToken());
    }
}
