package com.yanzi.common.trace.core;

import java.util.Date;

public interface ITraceInfo {
    public String getHostName();

    public Date getTime();

    public String getCategory();

    public String getTraceName();

    public String getTraceValue();
}
