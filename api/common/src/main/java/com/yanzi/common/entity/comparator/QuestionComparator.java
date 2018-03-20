package com.yanzi.common.entity.comparator;

import java.util.Comparator;

import com.yanzi.common.entity.college.question.QuestionInfo;

public class QuestionComparator implements Comparator<QuestionInfo> {
    @Override
    public int compare(QuestionInfo o1, QuestionInfo o2) {
        return o1.getIndex() > o2.getIndex() ? 1 : -1;
    }
}