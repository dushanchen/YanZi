package com.yanzi.common.constants;

public enum ValidValue {
    UNKNOW_VALUE(-1), 
    VALID(0),
    TEST(98),
    NOT_VALID(99);

    private int value;

    private ValidValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ValidValue getByName(String name) {
        try {
            return ValidValue.valueOf(name);
        } catch (Exception e) {
            return UNKNOW_VALUE;
        }
    }
}
