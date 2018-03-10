package com.yanzi.pisces.service.impl;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.service.impl.CUserCollegeServiceImpl;
import com.yanzi.pisces.mysql.LessonMapper;
import com.yanzi.pisces.service.LessonService;

public class LessonServiceImpl extends CUserCollegeServiceImpl implements LessonService{
	private LessonMapper lessonMapper;
	
	
	public LessonSummary selectSummaryBaseById(long lessonId){
		return lessonMapper.selectSummaryBaseById(lessonId);
	}
	
	public LessonPrimer selectPrimerById(long lessonId){
		return lessonMapper.selectPrimerById(lessonId);
	}
}
