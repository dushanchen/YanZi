package com.yanzi.pisces.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.yanzi.common.entity.college.question.QuestionInfo;

@Component
public class QuestionData {
    private Map<Long, QuestionInfo> questionMap = new ConcurrentHashMap<>();

    public QuestionInfo getQuestion(long id) {
        QuestionInfo result = questionMap.get(id);
        if (null == result) {
            return QuestionInfo.DEFAULT;
        }
        return result;
    }

    public Map<Long, QuestionInfo> getQuestionMap() {
        return questionMap;
    }

    public void setQuestionMap(Map<Long, QuestionInfo> questionMap) {
        this.questionMap = questionMap;
    }
}
