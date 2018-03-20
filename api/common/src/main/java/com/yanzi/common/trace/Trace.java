package com.yanzi.common.trace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.yanzi.common.trace.core.ITraceInfo;

public class Trace {
    private static Map<Long, JSONObject> traceMap = new ConcurrentHashMap<Long, JSONObject>();

    private static final String TRACK_ARRAY_KEY = "log";

    public static void openTrace() {
        Long key = getTraceKey();
        traceMap.remove(key);

        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        json.put(TRACK_ARRAY_KEY, jsonArray);
        traceMap.put(key, json);
    }

    public static void closeTrace() {
        Long key = getTraceKey();
        traceMap.remove(key);
    }

    public static JSONObject getTraceInfo() {
        Long key = getTraceKey();
        return traceMap.get(key);
    }

    private static Long getTraceKey() {
        return Thread.currentThread().getId();
    }

    public static void trace(ITraceInfo info) {
        Long key = getTraceKey();
        if (!traceMap.containsKey(key)) {
            return;
        }
        if (info.getCategory().equals(LogTraceInfo.TRACE_CATEGORY_LOG)) {
            trace(key, info);
        }
    }

    public static boolean isTrace() {
        Long key = getTraceKey();
        return traceMap.containsKey(key);
    }

    private static void trace(Long key, ITraceInfo info) {
        JSONObject element = new JSONObject();
        element.put(info.getCategory() + "_" + info.getTraceName(), info.getTraceValue());
        JSONObject traceInfo = traceMap.get(key);
        JSONArray traceArray = (JSONArray) traceInfo.get(TRACK_ARRAY_KEY);
        traceArray.add(element);
    }
}
