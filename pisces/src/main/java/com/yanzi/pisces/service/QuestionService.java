package com.yanzi.pisces.service;

import com.yanzi.common.entity.college.question.QuestionInfo;

public interface QuestionService {
    public QuestionInfo loadQuestion(long lessonId, int index);
}
