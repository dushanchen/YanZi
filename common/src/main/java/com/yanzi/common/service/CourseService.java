package com.yanzi.common.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.level.LevelInfo;

@Service
public class CourseService {

    private Map<Long, CourseInfo> courseMap = new ConcurrentHashMap<>();
    private List<CourseInfo> allCourseList = Collections.emptyList();
    private Map<Long, Map<Long, LevelInfo>> courseLevelMap = new ConcurrentHashMap<>();

    public LevelInfo getLevelByExp(long courseId, long exp) {
        Map<Long, LevelInfo> levelMap = courseLevelMap.get(courseId);
        if (null == levelMap || levelMap.isEmpty()) {
            return LevelInfo.DEFAULT;
        }
        LevelInfo levelResult = LevelInfo.DEFAULT;
        for (LevelInfo level : levelMap.values()) {
            if (exp >= level.getMinExp() && level.getLevel() > levelResult.getLevel()) {
                levelResult = level;
            }
        }
        return levelResult;
    }

    public List<CourseInfo> getAllCourseList() {
        return allCourseList;
    }

    public CourseInfo getCourseInfoById(long id) {
        return courseMap.get(id);
    }

    public boolean containsCourse(long id) {
        return courseMap.containsKey(id);
    }

    public void setAllCourseList(List<CourseInfo> allCurriculumList) {
        this.allCourseList = allCurriculumList;
    }

    public Map<Long, CourseInfo> getCourseMap() {
        return courseMap;
    }

    public void setCourseMap(Map<Long, CourseInfo> courseMap) {
        this.courseMap = courseMap;
    }

    public Map<Long, Map<Long, LevelInfo>> getCourseLevelMap() {
        return courseLevelMap;
    }

    public void setCourseLevelMap(
            Map<Long, Map<Long, LevelInfo>> courseLevelMap) {
        this.courseLevelMap = courseLevelMap;
    }
}
