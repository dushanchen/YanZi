package com.yanzi.pisces.entity;

public class RankInfo {
    public static final RankInfo DEFAULT = new RankInfo();

    private long userId;
    private long exp;
    private int rank;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
