package com.yanzi.common.service;

import java.util.List;

public interface CUserCollegeService {

    long loadCourseTermId(long userId, long courseId);

    void replaceCourseTermId(long userId, long courseId, long termId, boolean replace);

    long loadExp(long userId);

    List<Long> loadExp(List<Long> userIds);
    
    long loadWeekExp(long userId, String week);

    List<Long> loadWeekExp(List<Long> userIds, String week);

    long loadKnowledge(long userId);

    long loadCourseTermExp(long userId, long courseId, long termId);

    List<Long> loadCourseTermExp(List<Long> userIds, long courseId, long termId);

    long loadCourseTermWeekExp(long userId, long courseId, long termId, String week);

    List<Long> loadCourseTermWeekExp(List<Long> userIds, long courseId, long termId, String week);

    long loadCourseTermKnowledge(long userId, long courseId, long termId);

    long loadCourseTermDayKnowledge(long userId, long courseId, long termId, String day);

    long loadCourseTermCompleteDayCount(long userId, long courseId, long termId);

    boolean courseTermDayIsComplete(long userId, long courseId, long termId, String day);

    long loadCourseTermLessonKnowledge(long userId, long courseId, long termId, long lessonId);

    long loadCourseTermLessonMaxKnowledge(long userId, long courseId, long termId, long lessonId);

    boolean courseTermLessonIsComplete(long userId, long courseId, long termId, long lessonId);

    long loadCourseTermLevel(long userId, long courseId, long termId);
    
    long loadLatestLesson(long userId);
    
    void saveLatestLesson(long userId,long lessonId);
}
