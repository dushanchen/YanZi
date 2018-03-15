package com.yanzi.pisces.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermLesson;
import com.yanzi.common.entity.term.TermPrimer;

public interface TermMapper {
    public List<TermInfo> selectByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);

    public TermPrimer selectTermPrimerById(@Param(value = "termId") long termId);

    public List<TermCourse> selectTermCourseById(@Param(value = "termId") long termId,
            @Param(value = "valid") int valid);

    public List<TermLesson> selectTermLessonById(@Param(value = "termId") long termId,
            @Param(value = "valid") int valid);
    /**
     * 查询课程报名人数
     * @param termId
     * @author dsczijizuo
     * @return
     */
    public int countByTerm(@Param(value="termId") long termId);
}
