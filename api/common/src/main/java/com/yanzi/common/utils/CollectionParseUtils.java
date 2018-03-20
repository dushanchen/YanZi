package com.yanzi.common.utils;

import java.util.Collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class CollectionParseUtils {

    public static <T extends Number> void NumberParseString(Collection<T> src,
            Collection<String> desc, Class<T> clazz) {
        if (null == src || src.isEmpty()) {
            return;
        }
        if (clazz == Long.class) {
            for (Number item : src) {
                desc.add(Long.toString((Long) item));
            }
        } else if (clazz == Integer.class) {
            for (Number item : src) {
                desc.add(Integer.toString((Integer) item));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void StringParseNumber(Collection<String> src, Collection<T> desc,
            Class<T> clazz) {
        if (null == src || src.isEmpty()) {
            return;
        }
        if (clazz == Long.class) {
            for (String item : src) {
                if (null == item) {
                    desc.add((T) new Long(0));
                } else {
                    try {
                        desc.add((T) (new Long(Long.parseLong(item))));
                    } catch (NumberFormatException e) {
                        desc.add((T) new Long(0));
                    }
                }
            }
        } else if (clazz == Integer.class) {
            for (String item : src) {
                if (null == item) {
                    desc.add((T) new Integer(0));
                } else {
                    try {
                        desc.add((T) (new Integer(Integer.parseInt(item))));
                    } catch (NumberFormatException e) {
                        desc.add((T) new Integer(0));
                    }
                }
            }
        }
    }

    public static <T> void JsonStringParseObject(Collection<String> src, Collection<T> desc,
            Class<T> clazz) {
        if (null == src || src.isEmpty()) {
            return;
        }
        for (String item : src) {
            if (null == item) {
                try {
                    desc.add(clazz.newInstance());
                } catch (Exception e1) {
                }
                continue;
            }
            try {
                desc.add(JSON.parseObject(item, clazz));
            } catch (Exception e) {
                try {
                    desc.add(clazz.newInstance());
                } catch (Exception e1) {
                }
            }
        }
    }

    public static <T> void ObjectParseJsonString(Collection<T> src, Collection<String> desc,
            Class<T> clazz) {
        if (null == src || src.isEmpty()) {
            return;
        }
        for (T item : src) {
            desc.add(JSON.toJSONString(item));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void JsonArrayParseObject(JSONArray jsonArray, Collection<T> desc,
            Class<T> clazz) {
        if (null == jsonArray || jsonArray.isEmpty()) {
            return;
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            T t = null;
            if (clazz == Long.class) {
                t = (T) jsonArray.getLong(i);
            } else if (clazz == Integer.class) {
                t = (T) jsonArray.getInteger(i);
            } else if (clazz == String.class) {
                t = (T) jsonArray.getString(i);
            }
            desc.add(t);
        }
    }
}
