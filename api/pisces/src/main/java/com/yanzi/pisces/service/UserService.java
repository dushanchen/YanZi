package com.yanzi.pisces.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.service.CUserService;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.pisces.entity.LessonState;

public interface UserService extends CUserService {
	
	public long loadLatestLesson(long termId);
	
	public long selectUserTermIdByUserIdAndCourseId(long userId,long courseId);

	public List<Long> getUserByCourseIdTermId(long courseId, long termId);

	public List<TermCourse> selectUserTermIdByUserId(long userId);

	public List<Long> selectUserIdByTermId(long termId);

	public long loadFirstLesson(long termId);

	public LessonPrimer loadLessonPrimer(long lessonId);

	public int getStartLessonCount(long termId);

}
