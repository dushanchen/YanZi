package com.yanzi.pisces.controller.response;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.entity.user.UserInfo;
public class ViewShareCurriculumLessonResponse extends ViewResponseBase {
 
	 private UserInfo userInfo; 

	    private LessonInfo lessonInfo;
	    
	    private long knowledge;
	    
	    private long maxKnowledge;

		public UserInfo getUserInfo() {
			return userInfo;
		}

		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		public LessonInfo getLessonInfo() {
			return lessonInfo;
		}

		public void setLessonInfo(LessonInfo lessonInfo) {
			this.lessonInfo = lessonInfo;
		}

		public long getKnowledge() {
			return knowledge;
		}

		public void setKnowledge(long knowledge) {
			this.knowledge = knowledge;
		}

		public long getMaxKnowledge() {
			return maxKnowledge;
		}

		public void setMaxKnowledge(long maxKnowledge) {
			this.maxKnowledge = maxKnowledge;
		}
 
}
