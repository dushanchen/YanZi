package com.yanzi.pisces.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.service.impl.CUserServiceImpl;
import com.yanzi.pisces.mysql.UserCourseTermMapper;
import com.yanzi.pisces.service.UserService;


@Service
public class UserServiceImpl extends CUserServiceImpl implements UserService {
	
	 @Autowired
	  private UserCourseTermMapper userCourseTermMapper;
	 
	public long loadLatestLesson(long termId){
		return userCourseTermMapper.loadLatestLesson(termId);
	}
	
	public long selectUserTermIdByUserIdAndCourseId(long userId,long courseId){
		return userCourseTermMapper.selectUserTermIdByUserIdAndCourseId(userId,courseId);
	}
	
}
