package com.yanzi.common.trace;

import java.util.Date;

import com.yanzi.common.trace.core.ITraceInfo;
import com.yanzi.common.trace.util.NetUtil;

public abstract class TraceInfo implements ITraceInfo {
    private String hostName;

    private Date createTime;

    public TraceInfo() {
        hostName = NetUtil.getLocalName();
        createTime = new Date();
    }

    @Override
    public String getHostName() {
        return hostName;
    }

    @Override
    public Date getTime() {
        return createTime;
    }

}
