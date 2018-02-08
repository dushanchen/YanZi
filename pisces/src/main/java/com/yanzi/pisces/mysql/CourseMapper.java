package com.yanzi.pisces.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.course.CourseInfo;

public interface CourseMapper {
    public List<CourseInfo> selectByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);

	public List<CourseInfo> getAllCourseInfo();
}
