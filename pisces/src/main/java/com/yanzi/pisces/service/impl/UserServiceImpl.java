package com.yanzi.pisces.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.service.impl.CUserServiceImpl;
import com.yanzi.pisces.entity.LessonState;
import com.yanzi.pisces.mysql.UserCourseTermMapper;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.pisces.service.UserService;


@Service
public class UserServiceImpl extends CUserServiceImpl implements UserService {
	
	 @Autowired
	  private UserCourseTermMapper userCourseTermMapper;
	 
	 public int getStartLessonCount(long termId){
		 int count = 0;
		 List<LessonState> lessonStates=userCourseTermMapper.getStartLessonCount(termId);
		  for(LessonState lessonState:lessonStates){
	        	long now = System.currentTimeMillis();
	            if (now > lessonState.getStartTime()) {
	            	count++; //开课
	                break;
	            }
		  }
		  return count;	          
	 }
	 
	public long loadLatestLesson(long termId){
		return userCourseTermMapper.loadLatestLesson(termId); 
	}	
	
	public long loadFirstLesson(long termId){
		return userCourseTermMapper.loadFirstLesson(termId);
	}
	
	public LessonPrimer loadLessonPrimer(long lessonId){
		return userCourseTermMapper.loadLessonPrimer(lessonId);
	}
	
	public long selectUserTermIdByUserIdAndCourseId(long userId,long courseId){
		return userCourseTermMapper.selectUserTermIdByUserIdAndCourseId(userId,courseId);
	}

	@Override
	public List<TermCourse> selectUserTermIdByUserId(long userId) {
		
		return userCourseTermMapper.selectUserTermIdByUserId(userId);
	}

	@Override
	public List<Long> selectUserIdByTermId(long termId) {
		return userCourseTermMapper.selectUserIdByTermId(termId);
	}
	
	public List<Long> getUserByCourseIdTermId(long courseId, long termId){
		return userCourseTermMapper.getUserByCourseIdTermId(courseId,termId);
	}
}
