package com.yanzi.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapUtils {
    public static <T, K, V> Map<K, V> ensureConcurrentMapValue(Map<T, Map<K, V>> map, T key) {
        synchronized (map) {
            Map<K, V> value = map.get(key);
            if (value == null) {
                value = new ConcurrentHashMap<K, V>();
                map.put(key, value);
            }
            return value;
        }
    }

    public static <T, K, V> Map<K, V> ensureMapValue(Map<T, Map<K, V>> map, T key) {
        Map<K, V> value = map.get(key);
        if (value == null) {
            value = new HashMap<K, V>();
            map.put(key, value);
        }
        return value;
    }
}