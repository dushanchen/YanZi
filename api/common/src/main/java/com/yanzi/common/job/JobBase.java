package com.yanzi.common.job;

import java.util.List;

import org.slf4j.Logger;

import com.yanzi.common.trace.MLogger;

public abstract class JobBase {
    private static final Logger logger = new MLogger(JobBase.class);

    private List<JobBase> reloadJobNames;
    private List<JobBase> depJobNames;
    private boolean needReloadOtherJob = false;
    private boolean jobIsDone = false;

    protected abstract void run();

    public synchronized void jobRun() {
        try {
            if (depJobIsDone()) {
                run();
                reloadJobs();
                jobIsDone = true;
            }
        } catch (Exception e) {
            logger.error("job run failed, job name:{}", getClass().getSimpleName(), e);
        }
    }

    protected synchronized void setJobReload() {
    }

    private boolean depJobIsDone() {
        if (depJobNames == null) {
            return true;
        }
        for (JobBase job : depJobNames) {
            if (!job.isJobIsDone()) {
                logger.info("job: {} dep job:{} is not done, try later", getClass().getSimpleName(),
                        job.getClass().getSimpleName());
                return false;
            }
        }
        return true;
    }

    private void reloadJobs() {
        if (null == reloadJobNames || !needReloadOtherJob) {
            return;
        }
        for (JobBase job : reloadJobNames) {
            job.setJobReload();
        }
        needReloadOtherJob = false;
    }

    protected void beforeRun() {
    }

    protected void afterRun() {
    }

    public void setReloadJobNames(List<JobBase> reloadJobNames) {
        this.reloadJobNames = reloadJobNames;
    }

    public void setDepJobNames(List<JobBase> depJobNames) {
        this.depJobNames = depJobNames;
    }

    protected void setNeedReloadOtherJob(boolean needReloadOtherJob) {
        this.needReloadOtherJob = needReloadOtherJob;
    }

    public boolean isJobIsDone() {
        return jobIsDone;
    }
}
