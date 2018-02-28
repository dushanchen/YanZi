package com.yanzi.common.entity.user;

import com.alibaba.fastjson.annotation.JSONField;

public class BillsInfo {
	@JSONField(serialize = false)
	public static final BillsInfo DEFAULT = new BillsInfo();
	private long userid;
	private long courseid;
	private long termid;
	private boolean state;
	private long number;
	public BillsInfo() {
    }

    public BillsInfo(long userid) {
        this.userid = userid;
    }

    public BillsInfo(long userid,boolean state,long number) {
        this.userid =userid;
        this.state = state;
        this.number = number;
    }
    
    public BillsInfo(long userid,long courseid,long termid,boolean state,long number) {
        this.userid =userid;
        this.courseid=courseid;
        this.termid=termid;
        this.state = state;
        this.number = number;
    }
    
    public long getUserId(){
        return userid;
    }

    public long setUserId(long userid) {
        return this.userid = userid;
    }
    
    public long getCourseId() {
        return courseid;
    }

    public long setCourseId(long courseid) {
        return this.courseid = courseid;
    }
    
    public long getTermId() {
        return termid;
    }

    public long setTermId(long termid) {
        return this.termid = termid;
    }
    
    public boolean getState() {
        return state;
    }

    public boolean setState(boolean state) {
        return this.state = state;
    }
    
    public long getNumber() {
        return number;
    }

    public long setNumber(long number) {
        return this.number = number;
    }
    
    
    
    
}
