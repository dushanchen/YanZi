package com.yanzi.pisces.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.yanzi.common.entity.comparator.TermComparator;
import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermLesson;
import com.yanzi.common.entity.term.TermPrimer;

@Component
public class TermData implements InitializingBean {
    private Map<Long, TermInfo> termInfoMap = new ConcurrentHashMap<>();
    private Map<Long, TermPrimer> termPrimerMap = new ConcurrentHashMap<>();
    private Map<Long, List<TermCourse>> termCourseListMap = new ConcurrentHashMap<>();
    private Map<Long, List<TermLesson>> termLessonListMap = new ConcurrentHashMap<>();

    private TermComparator comparator;

    public boolean isValid(long id) {
        TermInfo termInfo = termInfoMap.get(id);
        if (null == termInfo) {
            return false;
        }
        long now = System.currentTimeMillis();
        if (now > termInfo.getEndTime()) {
            return false;
        }
        return true;
    }

    public boolean isStart(long id) {
        TermInfo termInfo = termInfoMap.get(id);
        if (null == termInfo) {
            return false;
        }
        return isStart(termInfo);
    }

    public boolean isStart(TermInfo termInfo) {
        long now = System.currentTimeMillis();
        if (now < termInfo.getStartTime()) {
            return false;
        }
        return true;
    }
/**
 * 获取已开课的学期，开学时间小于当前dusc
 * @return
 */
    public List<TermInfo> getSaleValidList() {
        Collection<TermInfo> allTerms = termInfoMap.values();
        List<TermInfo> result = new ArrayList<>();
        for (TermInfo termInfo : allTerms) {
            //if (isStart(termInfo)) {
                result.add(termInfo);
            //}
        }
        Collections.sort(result, comparator);
        return result;
    }

    public TermInfo get(long id) {
        TermInfo termInfo = termInfoMap.get(id);
        if (null == termInfo) {
            return TermInfo.DEFAULT;
        }
        return termInfo;
    }

    public TermPrimer getTermPrimer(long id) {
        TermPrimer termPrimer = termPrimerMap.get(id);
        if (null == termPrimer) {
            return TermPrimer.DEFAULT;
        }
        return termPrimer;
    }

    public List<TermCourse> getTermCourseList(long id) {
        List<TermCourse> termCourseList = termCourseListMap.get(id);
        if (null == termCourseList) {
            return Collections.emptyList();
        }
        return termCourseList;
    }

    public List<TermLesson> getTermLessonList(long id) {
        List<TermLesson> termLessonList = termLessonListMap.get(id);
        if (null == termLessonList) {
            return Collections.emptyList();
        }
        return termLessonList;
    }

    public Map<Long, TermInfo> getTermInfoMap() {
        return termInfoMap;
    }

    public void setTermInfoMap(Map<Long, TermInfo> termInfoMap) {
        this.termInfoMap = termInfoMap;
    }

    public Map<Long, List<TermCourse>> getTermCourseListMap() {
        return termCourseListMap;
    }

    public void setTermCourseListMap(Map<Long, List<TermCourse>> termCourseListMap) {
        this.termCourseListMap = termCourseListMap;
    }

    public Map<Long, List<TermLesson>> getTermLessonListMap() {
        return termLessonListMap;
    }

    public void setTermLessonListMap(Map<Long, List<TermLesson>> termLessonListMap) {
        this.termLessonListMap = termLessonListMap;
    }

    public Map<Long, TermPrimer> getTermPrimerMap() {
        return termPrimerMap;
    }

    public void setTermPrimerMap(Map<Long, TermPrimer> termPrimermap) {
        this.termPrimerMap = termPrimermap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        comparator = new TermComparator();
    }
}
