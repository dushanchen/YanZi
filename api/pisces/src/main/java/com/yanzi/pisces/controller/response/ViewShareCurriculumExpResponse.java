package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.entity.user.UserInfo;
public class ViewShareCurriculumExpResponse extends ViewResponseBase {
 
    private UserInfo userInfo; 

    private long exp;
    
    private LevelInfo levelInfo;
    
    private int beatCount;
    
    
    public int getBeatCount() {
		return beatCount;
	}

	public void setBeatCount(int beatCount) {
		this.beatCount = beatCount;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public LevelInfo getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(LevelInfo levelInfo) {
		this.levelInfo = levelInfo;
	}

 
}
