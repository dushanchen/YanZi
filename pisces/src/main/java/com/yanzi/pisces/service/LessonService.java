package com.yanzi.pisces.service;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.service.CUserCollegeService;

public interface LessonService extends CUserCollegeService{
	
	public LessonSummary selectSummaryBaseById(long lessonId);
	
	public LessonPrimer selectPrimerById(long lessonId);
}
