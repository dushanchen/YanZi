package com.yanzi.pisces.service;

import java.util.List;


import com.yanzi.common.service.CUserCollegeService;
import com.yanzi.pisces.entity.UserLessonStatus;
import com.yanzi.pisces.entity.UserRank;
import com.yanzi.pisces.entity.UserCollegeStatus;
import com.yanzi.pisces.entity.UserCourseTermStatus;

public interface UserCollegeService extends CUserCollegeService {
    /**
     * 获取用户CourseId
     * 
     * @param userId
     * @return
     */
    public List<Long> loadCourseIdList(long userId);

    /**
     * 用户完成关卡
     * 
     * @param userId
     * @param courseId
     * @param lessonId
     * @param lessonKnowledge
     * 
     * @return 新增的exp
     */
    long completeLesson(long userId, long courseId, long lessonId, long lessonKnowledge);

    /**
     * 获取用户课程期的状态
     * 
     * @param userId
     * @param courseId
     * @param termId
     * @return
     */
    UserCourseTermStatus loadCourseTermStatus(long userId, long courseId, long termId);

    /**
     * 获取用户college的状态
     * 
     * @param userId
     * @return
     */
    UserCollegeStatus loadStatus(long userId);

    /**
     * 获取用户lesson状态
     * 
     * @param userId
     * @param courseId
     * @param termId
     * @param lessonId
     * @return
     */
    UserLessonStatus loadLessonStatus(long userId, long courseId, long termId, long lessonId);

    int loadRankValue(long userId);

    int loadCourseTermRankValue(long userId, long courseId, long termId);

    List<UserRank> loadRankList(long userId);

    List<UserRank> loadCourseTermRankList(long userId, long courseId, long termId);

    List<UserRank> loadWeekRankList(long userId);

    List<UserRank> loadCourseTermWeekRankList(long userId, long courseId, long termId);

    /**
     * 用户购买学期
     * @param userId
     * @param termId
     * @author dusc
     */
    void userPurchaseTerm(long userId,long courseId,long termId);
}
