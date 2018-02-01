package com.yanzi.pisces.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.question.QuestionInfo;
import com.yanzi.common.entity.college.question.QuestionTextInfo;

public interface QuestionMapper {
    public List<QuestionInfo> selectByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);

    public List<QuestionTextInfo> selectQuestionTextByQuestionId(
            @Param(value = "questionId") long questionId);
}
