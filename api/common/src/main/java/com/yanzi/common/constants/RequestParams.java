package com.yanzi.common.constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public interface RequestParams {
	public static final String EVENT_ID = "evid";
	public static final String RESPONSE_TYPE = "responseType";

    public static enum ResponseType {
        json, pb, json_simple, plain;

        private static Map<ResponseType, MediaType> map;

        static {
            map = new HashMap<ResponseType, MediaType>();
            map.put(json, MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"));
            map.put(pb, MediaType.APPLICATION_OCTET_STREAM);
            map.put(json_simple, MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8"));
            map.put(plain, MediaType.parseMediaType(MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8"));
        }

        public MediaType toMediaType() {
            return map.get(this);
        }
    }
}
