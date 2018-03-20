package com.yanzi.common.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.redis.user.CUserCollegeRedisDao;
import com.yanzi.common.redis.user.CUserRedisDao;
import com.yanzi.common.service.CUserCollegeService;

@Service("cUserCollegeService")
public class CUserCollegeServiceImpl implements CUserCollegeService {

    @Autowired
    private CUserCollegeRedisDao cUserCollegeRedisDao;
    
    @Autowired
    private CUserRedisDao cUserRedisDao;

    @Override
    public long loadCourseTermId(long userId, long courseId) {
        long termId = cUserCollegeRedisDao.loadCourseTerm(userId, courseId);
        if (termId == 0) {
            throw new CommonException(ReturnCode.USER_COURSE_TERM_IS_NOT_VALID);//判空
        }
        return termId;
    }

    @Override
    public void replaceCourseTermId(long userId, long courseId, long termId, boolean replace) {
        if (replace || !cUserCollegeRedisDao.containCourseTerm(userId, courseId)) {
            cUserCollegeRedisDao.saveCourseTerm(userId, courseId, termId);
        }
    }
    
    @Override
    public void replaceUserCoins(long userId,double coins) {
            UserInfo userInfo=cUserRedisDao.getUserInfoById(userId);
            userInfo.setCoins(userInfo.getCoins()-coins);
            cUserRedisDao.saveUserInfoById(userId, userInfo);
    }
    
    public void replaceReUserCoins(long userId,double coins) {
        UserInfo userInfo=cUserRedisDao.getUserInfoById(userId);
        userInfo.setCoins(userInfo.getCoins()+coins);
        cUserRedisDao.saveUserInfoById(userId, userInfo);
}

    @Override
    public long loadExp(long userId) {
        return cUserCollegeRedisDao.loadExp(userId);
    }

    @Override
    public List<Long> loadExp(List<Long> userIds) {
        return cUserCollegeRedisDao.loadExp(userIds);
    }

    protected void saveExp(long userId, long exp) {
        cUserCollegeRedisDao.saveExp(userId, exp);
    }

    @Override
    public long loadWeekExp(long userId, String week) {
        return cUserCollegeRedisDao.loadWeekExp(userId, week);
    }

    @Override
    public List<Long> loadWeekExp(List<Long> userIds, String week) {
        return cUserCollegeRedisDao.loadWeekExp(userIds, week);
    }

    protected void saveWeekExp(long userId, String week, long exp) {
        cUserCollegeRedisDao.saveWeekExp(userId, week, exp);
    }

    @Override
    public long loadKnowledge(long userId) {
        return cUserCollegeRedisDao.loadKnowledge(userId);
    }

    protected void saveKnowledge(long userId, long knowledge) {
        cUserCollegeRedisDao.saveKnowledge(userId, knowledge);
    }

    @Override
    public long loadCourseTermExp(long userId, long courseId, long termId) {
        return cUserCollegeRedisDao.loadCourseTermExp(userId, courseId, termId);
    }

    @Override
    public List<Long> loadCourseTermExp(List<Long> userIds, long courseId, long termId) {
        return cUserCollegeRedisDao.loadCourseTermExp(userIds, courseId, termId);
    }

    protected void saveCourseTermExp(long userId, long courseId, long termId, long exp) {
        cUserCollegeRedisDao.saveCourseTermExp(userId, courseId, termId, exp);
    }

    @Override
    public long loadCourseTermWeekExp(long userId, long courseId, long termId, String week) {
        return cUserCollegeRedisDao.loadCourseTermWeekExp(userId, courseId, termId, week);
    }

    @Override
    public List<Long> loadCourseTermWeekExp(List<Long> userIds, long courseId, long termId,
            String week) {
        return cUserCollegeRedisDao.loadCourseTermWeekExp(userIds, courseId, termId, week);
    }

    protected void saveCourseTermWeekExp(long userId, long courseId, long termId, String week,
            long exp) {
        cUserCollegeRedisDao.saveCourseTermWeekExp(userId, courseId, termId, week, exp);
    }

    @Override
    public long loadCourseTermKnowledge(long userId, long courseId, long termId) {
        return cUserCollegeRedisDao.loadCourseTermKnowledge(userId, courseId, termId);
    }

    protected void saveCourseTermKnowledge(long userId, long courseId, long termId,
            long knowledge) {
        cUserCollegeRedisDao.saveCourseTermKnowledge(userId, courseId, termId, knowledge);
    }

    @Override
    public long loadCourseTermDayKnowledge(long userId, long courseId, long termId, String day) {
        return cUserCollegeRedisDao.loadCourseTermDayKnowledge(userId, courseId, termId, day);
    }

    protected void saveCourseTermDayKnowledge(long userId, long courseId, long termId,
            long knowledge, String day) {
        cUserCollegeRedisDao.saveCourseTermDayKnowledge(userId, courseId, termId, knowledge, day);
    }

    @Override
    public long loadCourseTermCompleteDayCount(long userId, long courseId, long termId) {
        return cUserCollegeRedisDao.loadCourseTermCompleteDayCount(userId, courseId, termId);
    }

    public long loadCourseTermDayComplete(long userId, long courseId, long termId) {
    	Date currentTime = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    	String dateString = formatter.format(currentTime);
        return cUserCollegeRedisDao.loadCourseTermDayComplete(userId, courseId, termId,dateString);
    }

    protected void saveCourseTermCompleteDayCount(long userId, long courseId, long termId,
            long dayCount) {
        cUserCollegeRedisDao.saveCourseTermCompleteDayCount(userId, courseId, termId, dayCount);
    }
    
    
    @Override
    public boolean courseTermDayIsComplete(long userId, long courseId, long termId, String day) {
        return cUserCollegeRedisDao.containCourseTermDayComplete(userId, courseId, termId, day);
    }

    protected void courseTermDayComplete(long userId, long courseId, long termId, String day) {
        cUserCollegeRedisDao.saveCourseTermDayComplete(userId, courseId, termId, day);
    }

    @Override
    public boolean courseTermLessonIsComplete(long userId, long courseId, long termId,
            long lessonId) {
        return cUserCollegeRedisDao.containCourseTermLessonKnowledge(userId, courseId, termId,
                lessonId);
    }

    @Override
    public long loadCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId) {
        return cUserCollegeRedisDao.loadCourseTermLessonKnowledge(userId, courseId, termId,
                lessonId);
    }

    protected void saveCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId, long knowledge) {
        cUserCollegeRedisDao.saveCourseTermLessonKnowledge(userId, courseId, termId, lessonId,
                knowledge);
    }

    @Override
    public long loadCourseTermLessonMaxKnowledge(long userId, long courseId, long termId,
            long lessonId) {
        return cUserCollegeRedisDao.loadCourseTermLessonMaxKnowledge(userId, courseId, termId,
                lessonId);
    }

    protected void saveCourseTermLessonMaxKnowledge(long userId, long courseId, long termId,
            long lessonId, long knowledge) {
        cUserCollegeRedisDao.saveCourseTermLessonMaxKnowledge(userId, courseId, termId, lessonId,
                knowledge);
    }

    @Override
    public long loadCourseTermLevel(long userId, long courseId, long termId) {
        return cUserCollegeRedisDao.loadCourseTermLevel(userId, courseId, termId);
    }

    protected void saveCourseTermLevel(long userId, long courseId, long termId, long levelId) {
        cUserCollegeRedisDao.saveCourseTermLevel(userId, courseId, termId, levelId);
    }
    /*
    @Override
    public long loadLatestLesson(long userId,long courseId){
    	return cUserCollegeRedisDao.loadLatestLesson(userId,courseId);
    	
    }
    */
    public void saveLatestLesson(long userId,long lessonId){
    	cUserCollegeRedisDao.saveLatestLesson(userId, lessonId);
    }
    
    
}
