package com.yanzi.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.yanzi.common.constants.ValidValue;

public class YanziResponseUtils {

    public static boolean isValid(JSONObject response) {
        if (response.getInteger("code") != ValidValue.VALID.getValue()) {
            return false;
        }
        if (response.getInteger("success") != ValidValue.VALID.getValue()) {
            return false;
        }
        return true;
    }

    public static JSONObject getMessage(JSONObject response) {
        return response.getJSONObject("message");
    }
}
