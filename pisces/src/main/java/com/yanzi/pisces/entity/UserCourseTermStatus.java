package com.yanzi.pisces.entity;

import java.util.List;

import com.yanzi.common.entity.college.level.LevelInfo;

public class UserCourseTermStatus {
    private long exp;
    private long totalKnowledge;
    private long dayKnowledge;
    private long sustainedCompleteDayCount;
    private List<Boolean> weekCompleteStatus;
    private int rank;
    private LevelInfo level;

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getTotalKnowledge() {
        return totalKnowledge;
    }

    public void setTotalKnowledge(long totalKnowledge) {
        this.totalKnowledge = totalKnowledge;
    }

    public long getDayKnowledge() {
        return dayKnowledge;
    }

    public void setDayKnowledge(long dayKnowledge) {
        this.dayKnowledge = dayKnowledge;
    }

    public long getSustainedCompleteDayCount() {
        return sustainedCompleteDayCount;
    }

    public void setSustainedCompleteDayCount(long sustainedCompleteDayCount) {
        this.sustainedCompleteDayCount = sustainedCompleteDayCount;
    }

    public List<Boolean> getWeekCompleteStatus() {
        return weekCompleteStatus;
    }

    public void setWeekCompleteStatus(List<Boolean> weekCompleteStatus) {
        this.weekCompleteStatus = weekCompleteStatus;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public LevelInfo getLevel() {
        return level;
    }

    public void setLevel(LevelInfo level) {
        this.level = level;
    }
}
