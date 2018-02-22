package com.yanzi.pisces.job.mysql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.college.lesson.Summary;
import com.yanzi.common.entity.comparator.LessonComparator;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.pisces.data.CourseData;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.mysql.LessonMapper;

public class LessonIncrLoadJob extends MysqlLoadJob implements InitializingBean{
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(LessonIncrLoadJob.class);

    @Autowired
    private CourseData courseData;
    @Autowired
    private LessonData lessonData;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, LessonInfo> lessonInfoMap;
    private Map<Long, List<Long>> courseLessonIdListMap;
    private Map<Long, List<Long>> newCourseLessonIdListMap = new HashMap<>();
    private Map<Long, LessonPrimer> lessonBriefMap;
    private Map<Long, LessonSummary> lessonSummaryMap;
    
    private LessonComparator comparator;

    @Override
    protected void beforeRun() {
        lessonInfoMap = lessonData.getLessonInfoMap();
        lessonBriefMap = lessonData.getLessonBriefMap();
        lessonSummaryMap = lessonData.getLessonSummaryMap();
        courseLessonIdListMap = courseData.getCourseLessonIdListMap();
        newCourseLessonIdListMap.clear();
    }

    @Override
    protected void mysqlLoad() {
        List<LessonInfo> lessonInfoList = lessonMapper.selectByRangTime(beginTime, endTime);
        if (null == lessonInfoList || lessonInfoList.isEmpty()) {
            return;
        }
        for (LessonInfo lessonInfo : lessonInfoList) {
            LessonInfo oldLessonInfo = lessonInfoMap.get(lessonInfo.getId());
            oldDataProcess(oldLessonInfo);
            newDataProcess(lessonInfo);
            if(envUtils.isValid(lessonInfo.getValid())){
                buildLesson(lessonInfo);
            }
        }
    }

    private void buildLesson(LessonInfo lessonInfo){
        long lessonId = lessonInfo.getId();
        LessonPrimer lessonBrief = lessonMapper.selectPrimerById(lessonId);
        lessonBriefMap.put(lessonId, lessonBrief);
        LessonSummary lessonSummary = lessonMapper.selectSummaryBaseById(lessonId);
//        List<Summary> summaries = lessonMapper.selectSummaryDetailById(lessonId, envUtils.getEnvValid().getValue());
        String summaryContent = lessonInfo.getSummaryContent();
        
        List<Summary> summaries = new Gson().fromJson(summaryContent, new TypeToken<List<Summary>>() {}.getType()); 
        
        for(int i=0;i<summaries.size();i++){
        	summaries.get(i).setLessonId(lessonId);
        }
        lessonSummary.setSummaries(summaries);
        lessonSummaryMap.put(lessonId, lessonSummary);
    }

    private void oldDataProcess(LessonInfo lessonInfo) {
        if (null == lessonInfo) {
            return;
        }
        long courseId = lessonInfo.getCourseId();
        Long lessonId = lessonInfo.getId();
        List<Long> courseLessonIdList = newCourseLessonIdListMap.get(courseId);
        if (null == courseLessonIdList) {
            courseLessonIdList = courseLessonIdListMap.get(courseId);
        }
        if (null != courseLessonIdList) {
            courseLessonIdList.remove(lessonId);
            newCourseLessonIdListMap.put(lessonId, courseLessonIdList);
        }
    }

    private void newDataProcess(LessonInfo lessonInfo) {
        if (null == lessonInfo) {
            return;
        }
        if (envUtils.isNotValid(lessonInfo.getValid())) {
            lessonInfoMap.remove(lessonInfo.getId());
            return;
        }
        lessonInfoMap.put(lessonInfo.getId(), lessonInfo);
        long courseId = lessonInfo.getCourseId();
        List<Long> lessonIdList = newCourseLessonIdListMap.get(courseId);
        if (null == lessonIdList) {
            lessonIdList = courseLessonIdListMap.get(courseId);
            if(null == lessonIdList) {
                lessonIdList = new ArrayList<>();
            }
            newCourseLessonIdListMap.put(courseId, lessonIdList);
        }
        lessonIdList.add(lessonInfo.getId());
    }

    @Override
    protected void afterRun() {
        for (Entry<Long, List<Long>> entry : newCourseLessonIdListMap.entrySet()) {
            long courseId = entry.getKey();
            List<Long> lessonIdList = entry.getValue();
            List<LessonInfo> lessonInfoList = new ArrayList<>();
            for (long lessonId : lessonIdList) {
                LessonInfo lesson = lessonInfoMap.get(lessonId);
                if (null == lesson) {
                    // TOOD log
                    continue;
                }
                lessonInfoList.add(lesson);
            }
            Collections.sort(lessonInfoList, comparator);
            lessonIdList.clear();
            for (LessonInfo lessonInfo : lessonInfoList) {
                lessonIdList.add(lessonInfo.getId());
            }
            courseLessonIdListMap.put(courseId, lessonIdList);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        comparator = new LessonComparator();
    }
}
