package com.yanzi.common.redis.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yanzi.common.constants.RedisPrefixCode;
import com.yanzi.common.redis.RedisBaseDao;
import com.yanzi.common.redis.user.CUserCollegeRedisDao;
import com.yanzi.common.utils.CollectionParseUtils;

@Service("cUserCollegeRedisDao")
public class CUserCollegeRedisDaoImpl extends RedisBaseDao implements CUserCollegeRedisDao {

    private String getUserCourseTermPrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_ID_TO_TERM_ID.getCode();
    }

    private String getUserCourseTermHashKey(long userId, long courseId) {
        return String.format("%s_%s", userId, courseId);
    }

    @Override
    public boolean containCourseTerm(long userId, long courseId) {
        String key = getUserCourseTermPrefix();
        String hashKey = getUserCourseTermHashKey(userId, courseId);
        return this.containsHashKey(key, hashKey);
    }

    @Override
    public long loadCourseTerm(long userId, long courseId) {
        String key = getUserCourseTermPrefix();
        String hashKey = getUserCourseTermHashKey(userId, courseId);
        String termIdStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(termIdStr)) {
            return 0;
        }
        return Long.parseLong(termIdStr);
    }

    @Override
    public void saveCourseTerm(long userId, long courseId, long termId) {
        String key = getUserCourseTermPrefix();
        String hashKey = getUserCourseTermHashKey(userId, courseId);
        this.cacheHash(key, hashKey, Long.toString(termId));
    }

    private String getUserExpPrefix() {
        return RedisPrefixCode.USER_COLLEGE_EXP.getCode();
    }

    @Override
    public long loadExp(long userId) {
        String key = getUserExpPrefix();
        String expStr = this.getHash(key, Long.toString(userId));
        if (StringUtils.isEmpty(expStr)) {
            return 0;
        }
        return Long.parseLong(expStr);
    }

    @Override
    public List<Long> loadExp(List<Long> userIds) {
        String key = getUserExpPrefix();
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> userIdStrs = new ArrayList<>();
        CollectionParseUtils.NumberParseString(userIds, userIdStrs, Long.class);
        List<String> expStrs = getHash(key, userIdStrs);
        List<Long> exps = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(expStrs, exps, Long.class);
        return exps;
    }

    @Override
    public void saveExp(long userId, long exp) {
        String key = getUserExpPrefix();
        this.cacheHash(key, Long.toString(userId), Long.toString(exp));
    }

    private String getUserKnowledgePrefix() {
        return RedisPrefixCode.USER_COLLEGE_KNOWLEDGE.getCode();
    }

    @Override
    public long loadKnowledge(long userId) {
        String key = getUserKnowledgePrefix();
        String knowledgeStr = this.getHash(key, Long.toString(userId));
        if (StringUtils.isEmpty(knowledgeStr)) {
            return 0;
        }
        return Long.parseLong(knowledgeStr);
    }

    @Override
    public void saveKnowledge(long userId, long knowledge) {
        String key = getUserKnowledgePrefix();
        this.cacheHash(key, Long.toString(userId), Long.toString(knowledge));
    }

    private String getUserWeekExpPrefix() {
        return RedisPrefixCode.USER_COLLEGE_WEEK_EXP.getCode();
    }

    private String getUserWeekExpHashKey(long userId, String week) {
        return String.format("%s_%s", userId, week);
    }

    @Override
    public long loadWeekExp(long userId, String week) {
        String k = getUserWeekExpPrefix();
        String hk = getUserWeekExpHashKey(userId, week);
        String expStr = this.getHash(k, hk);
        if (StringUtils.isEmpty(expStr)) {
            return 0;
        }
        return Long.parseLong(expStr);
    }

    @Override
    public List<Long> loadWeekExp(List<Long> userIds, String week) {
        String k = getUserWeekExpPrefix();
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> hks = new ArrayList<>();
        for (long userId : userIds) {
            String hk = getUserWeekExpHashKey(userId, week);
            hks.add(hk);
        }
        List<String> expStrs = getHash(k, hks);
        List<Long> exps = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(expStrs, exps, Long.class);
        return exps;
    }

    @Override
    public void saveWeekExp(long userId, String week, long exp) {
        String k = getUserWeekExpPrefix();
        String hk = getUserWeekExpHashKey(userId, week);
        this.cacheHash(k, hk, Long.toString(exp));
    }

    private String getUserCourseTermExpPrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_EXP.getCode();
    }

    private String getUserCourseTermExpHashKey(long userId, long courseId, long termId) {
        return String.format("%s_%s_%s", userId, courseId, termId);
    }

    @Override
    public long loadCourseTermExp(long userId, long courseId, long termId) {
        String key = getUserCourseTermExpPrefix();
        String hashKey = getUserCourseTermExpHashKey(userId, courseId, termId);
        String expStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(expStr)) {
            return 0;
        }
        return Long.parseLong(expStr);
    }

    @Override
    public List<Long> loadCourseTermExp(List<Long> userIds, long courseId, long termId) {
        String k = getUserCourseTermExpPrefix();
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> hks = new ArrayList<>();
        for (long userId : userIds) {
            String hk = getUserCourseTermExpHashKey(userId, courseId, termId);
            hks.add(hk);
        }
        List<String> expStrs = getHash(k, hks);
        List<Long> exps = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(expStrs, exps, Long.class);
        return exps;
    }

    @Override
    public void saveCourseTermExp(long userId, long courseId, long termId, long exp) {
        String key = getUserCourseTermExpPrefix();
        String hashKey = getUserCourseTermExpHashKey(userId, courseId, termId);
        this.cacheHash(key, hashKey, Long.toString(exp));
    }

    private String getUserCourseTermWeekExpPrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_WEEK_EXP.getCode();
    }

    private String getUserCourseTermWeekExpHashKey(long userId, long courseId, long termId,
            String week) {
        return String.format("%s_%s_%s_%s", userId, courseId, termId, week);
    }

    @Override
    public long loadCourseTermWeekExp(long userId, long courseId, long termId, String week) {
        String k = getUserCourseTermWeekExpPrefix();
        String hk = getUserCourseTermWeekExpHashKey(userId, courseId, termId, week);
        String expStr = this.getHash(k, hk);
        if (StringUtils.isEmpty(expStr)) {
            return 0;
        }
        return Long.parseLong(expStr);
    }

    @Override
    public List<Long> loadCourseTermWeekExp(List<Long> userIds, long courseId, long termId,
            String week) {
        String k = getUserCourseTermWeekExpPrefix();
        if (null == userIds || userIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> hks = new ArrayList<>();
        for (long userId : userIds) {
            String hk = getUserCourseTermWeekExpHashKey(userId, courseId, termId, week);
            hks.add(hk);
        }
        List<String> expStrs = getHash(k, hks);
        List<Long> exps = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(expStrs, exps, Long.class);
        return exps;
    }

    @Override
    public void saveCourseTermWeekExp(long userId, long courseId, long termId, String week,
            long exp) {
        String k = getUserCourseTermWeekExpPrefix();
        String hk = getUserCourseTermWeekExpHashKey(userId, courseId, termId, week);
        this.cacheHash(k, hk, Long.toString(exp));
    }

    private String getUserCourseTermKnowledgePrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_KNOWLEDGE.getCode();
    }

    private String getUserCourseTermKnowledgeHashKey(long userId, long courseId, long termId) {
        return String.format("%s_%s_%s", userId, courseId, termId);
    }

    @Override
    public long loadCourseTermKnowledge(long userId, long courseId, long termId) {
        String key = getUserCourseTermKnowledgePrefix();
        String hashKey = getUserCourseTermKnowledgeHashKey(userId, courseId, termId);
        String knowledgeStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(knowledgeStr)) {
            return 0;
        }
        return Long.parseLong(knowledgeStr);
    }

    @Override
    public void saveCourseTermKnowledge(long userId, long courseId, long termId, long knowledge) {
        String key = getUserCourseTermKnowledgePrefix();
        String hashKey = getUserCourseTermKnowledgeHashKey(userId, courseId, termId);
        this.cacheHash(key, hashKey, Long.toString(knowledge));
    }

    private String getUserCourseTermDayKnowledgePrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_KNOWLEDGE.getCode();
    }

    private String getUserCourseTermDayKnowledgeHashKey(long userId, long courseId, long termId,
            String day) {
        return String.format("%s_%s_%s_%s", userId, courseId, termId, day);
    }

    @Override
    public long loadCourseTermDayKnowledge(long userId, long courseId, long termId, String day) {
        String key = getUserCourseTermDayKnowledgePrefix();
        String hashKey = getUserCourseTermDayKnowledgeHashKey(userId, courseId, termId, day);
        String knowledgeStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(knowledgeStr)) {
            return 0;
        }
        return Long.parseLong(knowledgeStr);
    }

    @Override
    public void saveCourseTermDayKnowledge(long userId, long courseId, long termId, long knowledge,
            String day) {
        String key = getUserCourseTermDayKnowledgePrefix();
        String hashKey = getUserCourseTermDayKnowledgeHashKey(userId, courseId, termId, day);
        this.cacheHash(key, hashKey, Long.toString(knowledge));
    }

    private String getUserCourseTermCompleteDayCountPrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_COMPLETE_DAY_COUNT.getCode();
    }

    private String getUserCourseTermCompleteDayCountHashKey(long userId, long courseId,
            long termId) {
        return String.format("%s_%s_%s", userId, courseId, termId);
    }

    @Override
    public long loadCourseTermCompleteDayCount(long userId, long courseId, long termId) {
        String key = getUserCourseTermCompleteDayCountPrefix();
        String hashKey = getUserCourseTermCompleteDayCountHashKey(userId, courseId, termId);
        String countStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(countStr)) {
            return 0;
        }
        return Long.parseLong(countStr);
    }

    @Override
    public void saveCourseTermCompleteDayCount(long userId, long courseId, long termId,
            long dayCount) {
        String key = getUserCourseTermCompleteDayCountPrefix();
        String hashKey = getUserCourseTermCompleteDayCountHashKey(userId, courseId, termId);
        this.cacheHash(key, hashKey, Long.toString(dayCount));
    }

    private String getUserCourseTermDayCompletePrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_DAY_COMPLETE.getCode();
    }

    private String getUserCourseTermDayCompleteHashKey(long userId, long courseId, long termId,
            String day) {
        return String.format("%s_%s_%s_%s", userId, courseId, termId, day);
    }

    @Override
    public boolean containCourseTermDayComplete(long userId, long courseId, long termId,
            String day) {
        String key = getUserCourseTermDayCompletePrefix();
        String hashKey = this.getUserCourseTermDayCompleteHashKey(userId, courseId, termId, day);
        return this.containsHashKey(key, hashKey);
    }

    @Override
    public void saveCourseTermDayComplete(long userId, long courseId, long termId, String day) {
        String key = getUserCourseTermDayCompletePrefix();
        String hashKey = this.getUserCourseTermDayCompleteHashKey(userId, courseId, termId, day);
        this.cacheHash(key, hashKey, "1");
    }

    private String getUserCourseTermLessonMaxKnowledgePrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_LESSON_MAX_KNOWLEDGE.getCode();
    }

    private String getUserCourseTermLessonMaxKnowledgeHashKey(long userId, long courseId,
            long termId, long lessonId) {
        return String.format("%s_%s_%s_%s", userId, courseId, termId, lessonId);
    }

    @Override
    public long loadCourseTermLessonMaxKnowledge(long userId, long courseId, long termId,
            long lessonId) {
        String key = getUserCourseTermLessonMaxKnowledgePrefix();
        String hashKey = getUserCourseTermLessonMaxKnowledgeHashKey(userId, courseId, termId,
                lessonId);
        String correctCountStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(correctCountStr)) {
            return 0;
        }
        return Long.parseLong(correctCountStr);
    }

    @Override
    public void saveCourseTermLessonMaxKnowledge(long userId, long courseId, long termId,
            long lessonId, long correctCount) {
        String key = getUserCourseTermLessonMaxKnowledgePrefix();
        String hashKey = getUserCourseTermLessonMaxKnowledgeHashKey(userId, courseId, termId,
                lessonId);
        this.cacheHash(key, hashKey, Long.toString(correctCount));
    }

    private String getUserCourseTermLessonKnowledgePrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_LESSON_KNOWLEDGE.getCode();
    }

    private String getUserCourseTermLessonKnowledgeHashKey(long userId, long courseId, long termId,
            long lessonId) {
        return String.format("%s_%s_%s_%s", userId, courseId, termId, lessonId);
    }

    @Override
    public long loadCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId) {
        String key = getUserCourseTermLessonKnowledgePrefix();
        String hashKey = getUserCourseTermLessonKnowledgeHashKey(userId, courseId, termId,
                lessonId);
        String correctCountStr = this.getHash(key, hashKey);
        if (StringUtils.isEmpty(correctCountStr)) {
            return 0;
        }
        return Long.parseLong(correctCountStr);
    }

    @Override
    public void saveCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId, long correctCount) {
        String key = getUserCourseTermLessonKnowledgePrefix();
        String hashKey = getUserCourseTermLessonKnowledgeHashKey(userId, courseId, termId,
                lessonId);
        this.cacheHash(key, hashKey, Long.toString(correctCount));
    }

    @Override
    public boolean containCourseTermLessonKnowledge(long userId, long courseId, long termId,
            long lessonId) {
        String key = getUserCourseTermLessonKnowledgePrefix();
        String hashKey = getUserCourseTermLessonKnowledgeHashKey(userId, courseId, termId,
                lessonId);
        return this.containsHashKey(key, hashKey);
    }

    private String getCourseTermLevelPrefix() {
        return RedisPrefixCode.USER_COLLEGE_COURSE_TERM_LEVEL.getCode();
    }

    private String getCourseTermLevelHashKey(long userId, long courseId, long termId) {
        return String.format("%s_%s_%s", userId, courseId, termId);
    }

    @Override
    public long loadCourseTermLevel(long userId, long courseId, long termId) {
        String k = getCourseTermLevelPrefix();
        String hk = getCourseTermLevelHashKey(userId, courseId, termId);
        String levelIdStr = getHash(k, hk);
        if (StringUtils.isEmpty(levelIdStr)) {
            return 0;
        }
        return Long.parseLong(levelIdStr);
    }

    @Override
    public void saveCourseTermLevel(long userId, long courseId, long termId, long levelId) {
        String k = getCourseTermLevelPrefix();
        String hk = getCourseTermLevelHashKey(userId, courseId, termId);
        cacheHash(k, hk, Long.toString(levelId));
    }
    
    
    private String getUserSubscribedCoursePrefixSet(long userId) {
        return String.format("%s_%sS", RedisPrefixCode.USER_ID_TO_IDOL.getCode(), userId);
    }

    private String getUserSubscribedCoursePrefixList(long userId) {
        return String.format("%s_%sL", RedisPrefixCode.USER_ID_TO_IDOL.getCode(), userId);
    }

    //用户绑定课程
    @Override
    public void subscribeCourseV2(long userId, Long courseId) {
    	String setKey = getUserSubscribedCoursePrefixSet(userId);
        String courseIdStr = Long.toString(courseId);
        String listKey = getUserSubscribedCoursePrefixList(userId);
        if (!containsSetValue(setKey, courseIdStr)) {
            cacheRightList(listKey, courseIdStr);
            cacheSet(setKey, courseIdStr);
        }
    }

    @Override
    public List<Long> getUserSubscribedCourseV2(long userId) {
    	String listKey = getUserSubscribedCoursePrefixList(userId);
        List<String> idolIdStrs = getList(listKey,0,0);
        List<Long> result = new ArrayList<>();
        CollectionParseUtils.StringParseNumber(idolIdStrs, result, Long.class);
        return result;
    }
}
