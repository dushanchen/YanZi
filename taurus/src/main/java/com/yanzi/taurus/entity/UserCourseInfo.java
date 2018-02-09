package com.yanzi.taurus.entity;

import java.util.List;

import com.yanzi.common.entity.college.level.LevelInfo;

public class UserCourseInfo {
    private long exp;
    private long knowledge;
    private List<LevelInfo> courseLevels;

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(long knowledge) {
        this.knowledge = knowledge;
    }

	public List<LevelInfo> getCourseLevels() {
		return courseLevels;
	}

	public void setCourseLevels(List<LevelInfo> courseLevels) {
		this.courseLevels = courseLevels;
	}

    
}
