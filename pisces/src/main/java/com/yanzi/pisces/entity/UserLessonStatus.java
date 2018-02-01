package com.yanzi.pisces.entity;

public class UserLessonStatus {
    private double correctPercent;
    
    private long exp;
    
    private boolean isComplete = false;

    public double getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(double correctPercent) {
        this.correctPercent = correctPercent;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
