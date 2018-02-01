package com.yanzi.common.entity.comparator;

import java.util.Comparator;

import com.yanzi.common.entity.college.lesson.LessonInfo;


public class LessonComparator implements Comparator<LessonInfo> {
    @Override
    public int compare(LessonInfo o1, LessonInfo o2) {
        return o1.getIndex() > o2.getIndex() ? 1 : -1;
    }
}