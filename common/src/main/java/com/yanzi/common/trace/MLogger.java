package com.yanzi.common.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;

public class MLogger implements Logger {

    Logger logger = null;

    public MLogger(Logger logger) {
        this.logger = logger;
    }

    public MLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return Trace.isTrace() || logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, msg));
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, format(
                    format, arg)));
        }
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (logger.isTraceEnabled()) {
            logger.trace(format, arguments);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, arrayFormat(
                    format, arguments)));
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, msg, t));
        }
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return Trace.isTrace() || logger.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, msg));
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, format(
                    format, arg)));
        }
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, format, argArray);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, arrayFormat(
                    format, argArray)));
        }
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        if (logger.isTraceEnabled(marker)) {
            logger.trace(marker, msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_TRACE, msg, t));
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return Trace.isTrace() || logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, msg));
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, format(
                    format, arg)));
        }
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (logger.isDebugEnabled()) {
            logger.debug(format, arguments);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, arrayFormat(
                    format, arguments)));
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, msg, t));
        }
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return Trace.isTrace() || logger.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, msg));
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, format(
                    format, arg)));
        }
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, format, argArray);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, arrayFormat(
                    format, argArray)));
        }
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        if (logger.isDebugEnabled(marker)) {
            logger.debug(marker, msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_DEBUG, msg, t));
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return Trace.isTrace() || logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, msg));
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, format(
                    format, arg)));
        }
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            logger.info(format, arguments);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, arrayFormat(
                    format, arguments)));
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, msg, t));
        }
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return Trace.isTrace() || logger.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, msg));
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, format(
                    format, arg)));
        }
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, format, argArray);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, arrayFormat(
                    format, argArray)));
        }
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        if (logger.isInfoEnabled(marker)) {
            logger.info(marker, msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_INFO, msg, t));
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return Trace.isTrace() || logger.isWarnEnabled();
    }

    @Override
    public void warn(String msg) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, msg));
        }
    }

    @Override
    public void warn(String format, Object arg) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, format(
                    format, arg)));
        }
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (logger.isWarnEnabled()) {
            logger.warn(format, arguments);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, arrayFormat(
                    format, arguments)));
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, msg, t));
        }
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return Trace.isTrace() || logger.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, msg));
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, format(
                    format, arg)));
        }
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, format, argArray);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, arrayFormat(
                    format, argArray)));
        }
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        if (logger.isWarnEnabled(marker)) {
            logger.warn(marker, msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_WARN, msg, t));
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return Trace.isTrace() || logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, msg));
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (logger.isErrorEnabled()) {
            logger.error(format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, format(
                    format, arg)));
        }
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isErrorEnabled()) {
            logger.error(format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (logger.isErrorEnabled()) {
            logger.error(format, arguments);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, arrayFormat(
                    format, arguments)));
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, msg, t));
        }
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return Trace.isTrace() || logger.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg) {
        if (logger.isErrorEnabled(marker)) {
            logger.error(marker, msg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, msg));
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        if (logger.isErrorEnabled()) {
            logger.error(marker, format, arg);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, format(
                    format, arg)));
        }
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        if (logger.isErrorEnabled()) {
            logger.error(marker, format, arg1, arg2);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, format(
                    format, arg1, arg2)));
        }
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        if (logger.isErrorEnabled()) {
            logger.error(marker, format, argArray);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, arrayFormat(
                    format, argArray)));
        }
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        if (logger.isErrorEnabled()) {
            logger.error(marker, msg, t);
        }
        if (Trace.isTrace()) {
            Trace.trace(new LogTraceInfo(LogTraceInfo.LOG_LEVEL_ERROR, msg, t));
        }
    }

    private String format(String messagePattern, Object arg) {
        return MessageFormatter.format(messagePattern, arg).getMessage();
    }

    private String format(String messagePattern, Object arg1, Object arg2) {
        return MessageFormatter.format(messagePattern, arg1, arg2).getMessage();
    }

    private String arrayFormat(String messagePattern, Object[] argArray) {
        return MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
    }
}
