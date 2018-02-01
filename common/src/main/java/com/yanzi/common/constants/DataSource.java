package com.yanzi.common.constants;

public enum DataSource {
    UNKNOW_SOURCE(-1),
    ;

    private int source;

    private DataSource(int source) {
        this.source = source;
    }

    public int getSource() {
        return source;
    }

    public static DataSource getByName(String name) {
        try {
            return DataSource.valueOf(name);
        } catch (Exception e) {
            return UNKNOW_SOURCE;
        }
    }
}
