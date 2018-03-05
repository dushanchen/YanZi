package com.yanzi.common.redis.user;

import java.util.Collection;
import java.util.List;

public interface CUserCollegeRedisDao {

    /**
     * 判断用户course是否已经绑定term
     * 
     * @param userId
     * @param courseId
     * @return
     */
    public boolean containCourseTerm(long userId, long courseId);

    /**
     * 获取用户course的term
     * 
     * @param userId
     * @param courseId
     * @return
     */
    public long loadCourseTerm(long userId, long courseId);

    /**
     * 记录用户course的term值
     * 
     * @param userId
     * @param courseId
     * @param termId
     */
    public void saveCourseTerm(long userId, long courseId, long termId);

    long loadExp(long userId);

    List<Long> loadExp(List<Long> userIds);

    void saveExp(long userId, long exp);

    long loadKnowledge(long userId);

    void saveKnowledge(long userId, long knowledge);

    long loadCourseTermExp(long userId, long courseId, long termId);

    List<Long> loadCourseTermExp(List<Long> userIds, long courseId, long termId);

    void saveCourseTermExp(long userId, long courseId, long termId, long exp);

    long loadCourseTermKnowledge(long userId, long courseId, long termId);

    void saveCourseTermKnowledge(long userId, long courseId, long termId, long exp);

    long loadCourseTermDayKnowledge(long userId, long courseId, long termId, String day);

    void saveCourseTermDayKnowledge(long userId, long courseId, long termId, long exp, String day);

    long loadCourseTermCompleteDayCount(long userId, long courseId, long termId);

    void saveCourseTermCompleteDayCount(long userId, long courseId, long termId, long dayCount);

    boolean containCourseTermDayComplete(long userId, long courseId, long termId, String day);

    void saveCourseTermDayComplete(long userId, long courseId, long termId, String day);

    long loadCourseTermLessonMaxKnowledge(long userId, long courseId, long termId, long lessonId);

    void saveCourseTermLessonMaxKnowledge(long userId, long courseId, long termId, long lessonId,
            long knowledge);

    long loadCourseTermLessonKnowledge(long userId, long courseId, long termId, long lessonId);

    void saveCourseTermLessonKnowledge(long userId, long courseId, long termId, long lessonId,
            long knowledge);

    boolean containCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId);

    long loadCourseTermLevel(long userId, long courseId, long termId);

    void saveCourseTermLevel(long userId, long courseId, long termId, long levelId);

    long loadWeekExp(long userId, String week);

    List<Long> loadWeekExp(List<Long> userIds, String week);

    void saveWeekExp(long userId, String week, long exp);

    long loadCourseTermWeekExp(long userId, long courseId, long termId, String week);

    List<Long> loadCourseTermWeekExp(List<Long> userIds, long courseId, long termId, String week);

    void saveCourseTermWeekExp(long userId, long courseId, long termId, String week, long exp);

	void subscribeCourseV2(long userId, Long courseId);

	void unsubscribeCourseV2(long userId, Long courseId);

	List<Long> getUserSubscribedCourseV2(long userId);
    
    void saveLatestLesson(long userId,long lessonId);
    
    //long  loadLatestLesson(long userId,long couseId);
}
