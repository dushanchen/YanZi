package com.yanzi.pisces.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.college.question.QuestionInfo;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.QuestionData;
import com.yanzi.pisces.service.QuestionService;

@Service
public class QuestionServiceImpl  implements QuestionService{
    @Autowired
    private LessonData lessonData;
    @Autowired
    private QuestionData questionData;

    public QuestionInfo loadQuestion(long lessonId, int index){
       List<Long> questionIdList = lessonData.getQuestionIdList(lessonId);
       if(questionIdList.size() < index || index <= 0) {
           return QuestionInfo.DEFAULT;
       }
       return questionData.getQuestion(questionIdList.get(index-1));
    }
}
