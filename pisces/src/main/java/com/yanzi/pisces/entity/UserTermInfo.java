package com.yanzi.pisces.entity;

import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermPrimer;

public class UserTermInfo {
    private TermInfo termInfo;
    private TermPrimer termPrimer;
    private UserTermStatus userTermStatus;
    private TermStatus termStatus;

    public UserTermInfo(TermInfo termInfo, TermPrimer termPrimer, UserTermStatus userTermStatus,
            TermStatus termStatus) {
        this.termInfo = termInfo;
        this.termPrimer = termPrimer;
        this.userTermStatus = userTermStatus;
        this.termStatus = termStatus;
    }

    public TermInfo getTermInfo() {
        return termInfo;
    }

    public void setTermInfo(TermInfo termInfo) {
        this.termInfo = termInfo;
    }

    public TermPrimer getTermPrimer() {
        return termPrimer;
    }

    public void setTermPrimer(TermPrimer termPrimer) {
        this.termPrimer = termPrimer;
    }

    public TermStatus getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(TermStatus termStatus) {
        this.termStatus = termStatus;
    }

    public UserTermStatus getUserTermStatus() {
        return userTermStatus;
    }

    public void setUserTermStatus(UserTermStatus userTermStatus) {
        this.userTermStatus = userTermStatus;
    }
}
