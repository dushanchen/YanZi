package com.yanzi.pisces.job.mysql;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermLesson;
import com.yanzi.common.entity.term.TermPrimer;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.pisces.data.TermData;
import com.yanzi.pisces.mysql.TermMapper;

public class TermIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(TermIncrLoadJob.class);

    @Autowired
    private TermData termData;
    @Autowired
    private TermMapper termMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, TermInfo> termInfoMap;
    private Map<Long, TermPrimer> termPrimerMap;
    private Map<Long, List<TermCourse>> termCourseListMap;
    private Map<Long, List<TermLesson>> termLessonListMap;

    @Override
    protected void beforeRun() {
        termInfoMap = termData.getTermInfoMap();
        termCourseListMap = termData.getTermCourseListMap();
        termLessonListMap = termData.getTermLessonListMap();
        termPrimerMap = termData.getTermPrimerMap();
    }

    @Override
    protected void mysqlLoad() {
        List<TermInfo> termInfoList = termMapper.selectByRangTime(beginTime, endTime);
        if (null == termInfoList || termInfoList.isEmpty()) {
            return;
        }
        for (TermInfo termInfo : termInfoList) {
            long termId = termInfo.getId();
            if (envUtils.isNotValid(termInfo.getValid())) {
                termInfoMap.remove(termId);
                termPrimerMap.remove(termId);
                termCourseListMap.remove(termId);
                termLessonListMap.remove(termId);
                continue;
            } else {
                termInfoMap.put(termId, termInfo);
                buildTerm(termInfo);
            }
        }
    }

    private void buildTerm(TermInfo termInfo) {
        long termId = termInfo.getId();
        TermPrimer termPrimer = termMapper.selectTermPrimerById(termId);
        //查询购买人数 dusc
        int count = termMapper.countByTerm(termId);
        termInfo.setCount(count);
        
        termPrimerMap.put(termId, termPrimer);
        List<TermCourse> termCourseList = termMapper.selectTermCourseById(termId,
                envUtils.getEnvValid().getValue());
        if (null != termCourseList && !termCourseList.isEmpty()) {
            termCourseListMap.put(termId, termCourseList);
        }
        List<TermLesson> termLessonList = termMapper.selectTermLessonById(termId,
                envUtils.getEnvValid().getValue());
        if (null != termLessonList && !termLessonList.isEmpty()) {
            termLessonListMap.put(termId, termLessonList);
        }
    }
}
