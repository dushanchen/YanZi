package com.yanzi.common.trace;

public class LogTraceInfo extends TraceInfo {

    /** trace category */
    public final static String TRACE_CATEGORY_LOG = "LOG";
    public final static String LOG_LEVEL_TRACE = "TRACE";
    public final static String LOG_LEVEL_DEBUG = "DEBUG";
    public final static String LOG_LEVEL_INFO = "INFO";
    public final static String LOG_LEVEL_WARN = "WARN";
    public final static String LOG_LEVEL_ERROR = "ERROR";
    public final static String LOG_LEVEL_FATAL = "FATAL";

    /** log level */
    private String level;
    /** log info */
    private Object log;
    /** throw info */
    private Throwable throwable = null;
    /** throw in text */
    private String throwText = "";

    public LogTraceInfo() {
        super();
    }

    public LogTraceInfo(String level, Object log) {
        super();
        this.level = level;
        this.log = log;
    }

    public LogTraceInfo(String level, Object log, Throwable throwable) {
        super();
        this.level = level;
        this.log = log;
        this.throwable = throwable;
    }

    public LogTraceInfo(String level, Object log, String throwText) {
        super();
        this.level = level;
        this.log = log;
        this.throwText = throwText;
    }

    public String getThrowInfo() {
        // return throw from throw text
        if (null == throwable) {
            return throwText;
        }
        // return from throwable;
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackArray = throwable.getStackTrace();
        for (int i = 0; i < stackArray.length; ++i) {
            StackTraceElement element = stackArray[i];
            sb.append(element.toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String getCategory() {
        return TRACE_CATEGORY_LOG;
    }

    @Override
    public String getTraceName() {
        return level;
    }

    @Override
    public String getTraceValue() {
        return log.toString() + getThrowInfo();
    }
}
