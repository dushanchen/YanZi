package com.yanzi.pisces.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.term.TermLesson;

@Component
public class LessonData {
    private Map<Long, LessonInfo> lessonInfoMap = new ConcurrentHashMap<>();

    private Map<Long, LessonPrimer> lessonBriefMap = new ConcurrentHashMap<>();

    private Map<Long, LessonSummary> lessonSummaryMap = new ConcurrentHashMap<>();

    private Map<Long, List<Long>> lessonQuestionIdListMap = new ConcurrentHashMap<>();
    
    private Map<Long, TermLesson> termLessonMap = new ConcurrentHashMap<>();

    public LessonInfo get(long id) {
        LessonInfo lessonInfo = lessonInfoMap.get(id);
        if (null == lessonInfo) {
            return LessonInfo.DEFAULT;
        }
        return lessonInfo;
    }

    public List<LessonInfo> get(List<Long> ids) {
        List<LessonInfo> result = new ArrayList<>();
        for (long id : ids) {
            LessonInfo lessonInfo = lessonInfoMap.get(id);
            if (null == lessonInfo) {
                result.add(LessonInfo.DEFAULT);
            } else {
                result.add(lessonInfo);
            }
        }
        return result;
    }

    public List<Long> getQuestionIdList(long id) {
        List<Long> questionIdList = lessonQuestionIdListMap.get(id);
        if (null == questionIdList) {
            return Collections.emptyList();
        }
        return questionIdList;
    }

    public int getQuestionCount(long id) {
        List<Long> questionIdList = lessonQuestionIdListMap.get(id);
        if (null == questionIdList) {
            return 0;
        }
        return questionIdList.size();
    }

    public TermLesson getTermLesson(long id) {
    	TermLesson termLesson = termLessonMap.get(id);
        if (null == termLesson) {
            return termLesson.DEFAULT;
        }
        return termLesson;
    }

    

	public LessonSummary getLessonSummary(long id) {
        LessonSummary lessonSummary = lessonSummaryMap.get(id);
        if (null == lessonSummary) {
            return LessonSummary.DEFAULT;
        }
        return lessonSummary;
    }
    
    public LessonPrimer getLessonBrief(long id) {
        LessonPrimer lessonBrief = lessonBriefMap.get(id);
        if (null == lessonBrief) {
            return LessonPrimer.DEFAULT;
        }
        return lessonBrief;
    }

    public Map<Long, LessonInfo> getLessonInfoMap() {
        return lessonInfoMap;
    }

    public void setLessonInfoMap(Map<Long, LessonInfo> lessonInfoMap) {
        this.lessonInfoMap = lessonInfoMap;
    }

    public Map<Long, List<Long>> getLessonQuestionIdListMap() {
        return lessonQuestionIdListMap;
    }

    public void setLessonQuestionIdListMap(Map<Long, List<Long>> lessonQuestionIdListMap) {
        this.lessonQuestionIdListMap = lessonQuestionIdListMap;
    }

    public Map<Long, LessonPrimer> getLessonBriefMap() {
        return lessonBriefMap;
    }

    public void setLessonBriefMap(Map<Long, LessonPrimer> lessonBriefMap) {
        this.lessonBriefMap = lessonBriefMap;
    }

    public Map<Long, LessonSummary> getLessonSummaryMap() {
        return lessonSummaryMap;
    }

    public void setLessonSummaryMap(Map<Long, LessonSummary> lessonSummaryMap) {
        this.lessonSummaryMap = lessonSummaryMap;
    }
    
    public Map<Long, TermLesson> getTermLessonMap() {
		return termLessonMap;
	}

	public void setTermLessonMap(Map<Long, TermLesson> termLessonMap) {
		this.termLessonMap = termLessonMap;
	}
}
