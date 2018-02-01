package com.yanzi.taurus.enums;

public enum SMSVerifiCodeType {
    UNKNOWN_ERROR(-1), USER_REGISTER(0), RESET_PASSWORD(1), RESET_PHONENO(2);
    private int type;

    private SMSVerifiCodeType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static SMSVerifiCodeType getByName(String name) {
        try {
            return SMSVerifiCodeType.valueOf(name);
        } catch (Exception e) {
            return UNKNOWN_ERROR;
        }
    }
}
