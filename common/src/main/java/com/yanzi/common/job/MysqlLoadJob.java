package com.yanzi.common.job;

import java.sql.Timestamp;

import org.slf4j.Logger;

import com.yanzi.common.trace.MLogger;

public abstract class MysqlLoadJob extends JobBase {

    private static final Logger logger = new MLogger(MysqlLoadJob.class);

    protected long lastLoadTime;
    protected Timestamp beginTime;
    protected Timestamp endTime;

    public MysqlLoadJob() {
        lastLoadTime = 0;
    }

    @Override
    protected void run() {
        beforeRun();
        // TODO time judge
        beginTime = new Timestamp(lastLoadTime - 60 * 1000);
        long now = System.currentTimeMillis();
        endTime = new Timestamp(now);
        mysqlLoad();
        lastLoadTime = now;
        afterRun();
        setNeedReloadOtherJob(true);
        logger.info("job:{} run completed, TimeRang:{}, {}", getClass().getSimpleName(),
                beginTime.getTime(), endTime.getTime());
    }

    protected abstract void mysqlLoad();

    @Override
    protected synchronized void setJobReload() {
        lastLoadTime = 0;
    }
}
