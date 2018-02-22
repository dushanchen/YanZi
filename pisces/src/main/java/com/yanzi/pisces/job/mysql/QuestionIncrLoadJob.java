package com.yanzi.pisces.job.mysql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanzi.common.entity.college.lesson.Summary;
import com.yanzi.common.entity.college.question.QuestionInfo;
import com.yanzi.common.entity.college.question.QuestionTextInfo;
import com.yanzi.common.entity.comparator.QuestionComparator;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.QuestionData;
import com.yanzi.pisces.mysql.QuestionMapper;

public class QuestionIncrLoadJob extends MysqlLoadJob implements InitializingBean{
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(QuestionIncrLoadJob.class);

    @Autowired
    private QuestionData questionData;
    @Autowired
    private LessonData lessonData;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, QuestionInfo> questionInfoMap;
    private Map<Long, List<Long>> lessonQuestionIdListMap;
    private Map<Long, List<Long>> newLessonQuestionIdListMap = new HashMap<>();
    
    private QuestionComparator comparator;

    @Override
    protected void beforeRun() {
        questionInfoMap = questionData.getQuestionMap();
        lessonQuestionIdListMap = lessonData.getLessonQuestionIdListMap();
        newLessonQuestionIdListMap.clear();
    }

    @Override
    protected void mysqlLoad() {
        List<QuestionInfo> questionInfoList = questionMapper.selectByRangTime(beginTime, endTime);
        if (null == questionInfoList || questionInfoList.isEmpty()) {
            return;
        }
        for (QuestionInfo questionInfo : questionInfoList) {
            QuestionInfo oldQuestionInfo = questionInfoMap.get(questionInfo.getId());
            oldDataProcess(oldQuestionInfo);
            newDataProcess(questionInfo);
            if(envUtils.isValid(questionInfo.getValid())) {
                buildQuestion(questionInfo);
            }
        }
    }

    private void buildQuestion(QuestionInfo questionInfo){
//        long questionId = questionInfo.getId();
//        List<QuestionTextInfo> questionTextInfoList = questionMapper.selectQuestionTextByQuestionId(questionId);
        try{
        	String jsonText = questionInfo.getJsonContent();
	        if(jsonText != null && !jsonText.equals("")){
	        	List<QuestionTextInfo> questionTextInfoList = new Gson().fromJson(jsonText, new TypeToken<List<QuestionTextInfo>>() {}.getType()); 
	        	questionInfo.build(questionTextInfoList);
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
    }

    private void oldDataProcess(QuestionInfo questionInfo) {
        if (null == questionInfo) {
            return;
        }
        long lessonId = questionInfo.getLessonId();
        Long questionId = questionInfo.getId();
        List<Long> lessonQuestionIdList = newLessonQuestionIdListMap.get(lessonId);
        if (null == lessonQuestionIdList) {
            lessonQuestionIdList = lessonQuestionIdListMap.get(lessonId);
        }
        if (null != lessonQuestionIdList) {
            lessonQuestionIdList.remove(questionId);
            newLessonQuestionIdListMap.put(lessonId, lessonQuestionIdList);
        }
    }

    private void newDataProcess(QuestionInfo questionInfo) {
        if (null == questionInfo) {
            return;
        }
        if (envUtils.isNotValid(questionInfo.getValid())) {
            questionInfoMap.remove(questionInfo.getId());
            return;
        }
        questionInfoMap.put(questionInfo.getId(), questionInfo);
        long lessonId = questionInfo.getLessonId();
        List<Long> questionIdList = newLessonQuestionIdListMap.get(lessonId);
        if (null == questionIdList) {
            questionIdList = lessonQuestionIdListMap.get(lessonId);
            if(null == questionIdList) {
                questionIdList = new ArrayList<>();
            }
            newLessonQuestionIdListMap.put(lessonId, questionIdList);
        }
        questionIdList.add(questionInfo.getId());
    }

    @Override
    protected void afterRun() {
        for (Entry<Long, List<Long>> entry : newLessonQuestionIdListMap.entrySet()) {
            long lessonId = entry.getKey();
            List<Long> questionIdList = entry.getValue();
            List<QuestionInfo> questionInfoList = new ArrayList<>();
            for (long questionId : questionIdList) {
                QuestionInfo questionInfo = questionInfoMap.get(questionId);
                if (null == questionInfo) {
                    // TOOD log
                    continue;
                }
                questionInfoList.add(questionInfo);
            }
            Collections.sort(questionInfoList, comparator);
            questionIdList.clear();
            for (QuestionInfo questionInfo : questionInfoList) {
                questionIdList.add(questionInfo.getId());
            }
            lessonQuestionIdListMap.put(lessonId, questionIdList);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        comparator = new QuestionComparator();
    }
}
