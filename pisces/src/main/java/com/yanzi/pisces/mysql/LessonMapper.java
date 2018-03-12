package com.yanzi.pisces.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.college.lesson.Summary;
import com.yanzi.common.entity.term.TermLesson;

public interface LessonMapper {
    public List<LessonInfo> selectByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);

    public LessonPrimer selectPrimerById(@Param(value = "lessonId") long lessonId);

    public LessonSummary selectSummaryBaseById(@Param(value = "lessonId") long lessonId);

    public List<Summary> selectSummaryDetailById(@Param(value = "lessonId") long lessonId, @Param(value = "valid") int valid);
}
