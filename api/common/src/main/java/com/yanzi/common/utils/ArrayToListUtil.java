package com.yanzi.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayToListUtil {
    public static <T> List<T> parse(T[] array) {
        List<T> result = new ArrayList<T>();
        if (null == array) {
            return result;
        }
        for (T item : array) {
            result.add(item);
        }
        return result;
    }

    public static List<Long> parse(long[] array) {
        List<Long> result = new ArrayList<Long>();
        if (null == array) {
            return result;
        }
        for (long item : array) {
            result.add(item);
        }
        return result;
    }

    public static List<Integer> parse(int[] array) {
        List<Integer> result = new ArrayList<>();
        if (null == array) {
            return result;
        }
        for (int item : array) {
            result.add(item);
        }
        return result;
    }

    public static List<Double> parse(double[] array) {
        List<Double> result = new ArrayList<>();
        if (null == array) {
            return result;
        }
        for (double item : array) {
            result.add(item);
        }
        return result;
    }
}
