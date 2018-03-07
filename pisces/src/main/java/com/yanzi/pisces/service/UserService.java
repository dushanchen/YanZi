package com.yanzi.pisces.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.service.CUserService;

public interface UserService extends CUserService {
	
	public long loadLatestLesson(long termId);
	
	public long selectUserTermIdByUserIdAndCourseId(long userId,long courseId);

	public List<Long> getUserByCourseIdTermId(long courseId, long termId);

}
