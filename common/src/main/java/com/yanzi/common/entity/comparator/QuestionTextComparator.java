package com.yanzi.common.entity.comparator;

import java.util.Comparator;

import com.yanzi.common.entity.college.question.QuestionTextInfo;

public class QuestionTextComparator implements Comparator<QuestionTextInfo> {
    @Override
    public int compare(QuestionTextInfo o1, QuestionTextInfo o2) {
        if (o1.getBlock() == o2.getBlock()) {
            return o1.getIndex() > o2.getIndex() ? 1 : -1;
        }
        return o1.getBlock() > o2.getBlock() ? 1 : -1;
    }
}