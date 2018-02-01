package com.yanzi.taurus.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserSource {
    UNKNOWN_ERROR(-1), PHONE_NO(0), WECHAT(1), QQ(2), SINA_BLOG(3);
    private int source;

    private UserSource(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    private static Map<Integer, UserSource> userSourceMap = new HashMap<>();

    static {
        userSourceMap.put(PHONE_NO.getSource(), PHONE_NO);
        userSourceMap.put(WECHAT.getSource(), WECHAT);
        userSourceMap.put(QQ.getSource(), QQ);
        userSourceMap.put(SINA_BLOG.getSource(), SINA_BLOG);
    }

    public static UserSource getBySource(int source) {
        UserSource result = userSourceMap.get(source);
        if (null == result) {
            return UNKNOWN_ERROR;
        }
        return result;
    }

    public static UserSource getByName(String name) {
        try {
            return UserSource.valueOf(name);
        } catch (Exception e) {
            return UNKNOWN_ERROR;
        }
    }
}
