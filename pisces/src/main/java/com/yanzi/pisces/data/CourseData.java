package com.yanzi.pisces.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.yanzi.common.entity.college.course.CourseInfo;

@Component
public class CourseData {
    private Map<Long, CourseInfo> courseInfoMap = new ConcurrentHashMap<>();

    private Map<Long, List<Long>> courseLessonIdListMap = new ConcurrentHashMap<>();

    private Map<Long, List<Long>> courseLevelIdListMap = new ConcurrentHashMap<>();

    public CourseInfo get(long id) {
        CourseInfo course = courseInfoMap.get(id);
        if (null == course) {
            return CourseInfo.DEFAULT;
        }
        return course;
    }

    public List<Long> getLessonIdList(long id) {
        List<Long> lessonIds = courseLessonIdListMap.get(id);
        if (null == lessonIds) {
            return Collections.emptyList();
        }
        return lessonIds;
    }

    public List<Long> getLevelIdList(long id) {
        List<Long> levelIds = courseLevelIdListMap.get(id);
        if (null == levelIds) {
            return Collections.emptyList();
        }
        return levelIds;
    }

    public Map<Long, List<Long>> getCourseLessonIdListMap() {
        return courseLessonIdListMap;
    }

    public void setCourseLessonIdListMap(Map<Long, List<Long>> courseLessonIdListMap) {
        this.courseLessonIdListMap = courseLessonIdListMap;
    }

    public Map<Long, CourseInfo> getCourseInfoMap() {
        return courseInfoMap;
    }

    public void setCourseInfoMap(Map<Long, CourseInfo> courseInfoMap) {
        this.courseInfoMap = courseInfoMap;
    }

    public Map<Long, List<Long>> getCourseLevelIdListMap() {
        return courseLevelIdListMap;
    }

    public void setCourseLevelIdListMap(Map<Long, List<Long>> courseLevelIdListMap) {
        this.courseLevelIdListMap = courseLevelIdListMap;
    }

}
