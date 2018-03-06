package com.yanzi.pisces.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.service.CUserService;

public interface UserService extends CUserService {
	
	public long loadLatestLesson(long termId);
	
	public long selectUserTermIdByUserIdAndCourseId(long userId,long courseId);

	public List<TermCourse> selectUserTermIdByUserId(long userId);

	public List<Long> selectUserIdByTermId(long termId);

}
