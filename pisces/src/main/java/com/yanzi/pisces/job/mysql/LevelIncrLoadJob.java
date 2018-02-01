package com.yanzi.pisces.job.mysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.pisces.data.CourseData;
import com.yanzi.pisces.data.LevelData;
import com.yanzi.pisces.mysql.LevelMapper;

public class LevelIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(LevelIncrLoadJob.class);

    @Autowired
    private CourseData courseData;
    @Autowired
    private LevelData levelData;
    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, LevelInfo> levelInfoMap;
    private Map<Long, List<Long>> courseLevelIdListMap;
    private Map<Long, List<Long>> newCourseLevelIdListMap = new HashMap<>();

    @Override
    protected void beforeRun() {
        levelInfoMap = levelData.getLevelInfoMap();
        courseLevelIdListMap = courseData.getCourseLevelIdListMap();
        newCourseLevelIdListMap.clear();
    }

    @Override
    protected void mysqlLoad() {
        List<LevelInfo> levelInfoList = levelMapper.selectByRangTime(beginTime, endTime);
        if (null == levelInfoList || levelInfoList.isEmpty()) {
            return;
        }
        for (LevelInfo levelInfo : levelInfoList) {
            LevelInfo oldLevelInfo = levelInfoMap.get(levelInfo.getId());
            oldDataProcess(oldLevelInfo);
            newDataProcess(levelInfo);
        }
    }

    private void oldDataProcess(LevelInfo levelInfo) {
        if (null == levelInfo) {
            return;
        }
        long courseId = levelInfo.getCourseId();
        Long levelId = levelInfo.getId();
        List<Long> levelIdList = newCourseLevelIdListMap.get(courseId);
        if (null == levelIdList) {
            levelIdList = courseLevelIdListMap.get(courseId);
        }
        if (null != levelIdList) {
            levelIdList.remove(levelId);
            newCourseLevelIdListMap.put(levelId, levelIdList);
        }
    }

    private void newDataProcess(LevelInfo levelInfo) {
        if (null == levelInfo) {
            return;
        }
        if (envUtils.isNotValid(levelInfo.getValid())) {
            levelInfoMap.remove(levelInfo.getId());
            return;
        }
        levelInfoMap.put(levelInfo.getId(), levelInfo);
        long courseId = levelInfo.getCourseId();
        List<Long> levelIdList = newCourseLevelIdListMap.get(courseId);
        if (null == levelIdList) {
            levelIdList = courseLevelIdListMap.get(courseId);
            if(null == levelIdList) {
                levelIdList = new ArrayList<>();
            }
            newCourseLevelIdListMap.put(courseId, levelIdList);
        }
        levelIdList.add(levelInfo.getId());
    }

    @Override
    protected void afterRun() {
        for (Entry<Long, List<Long>> entry : newCourseLevelIdListMap.entrySet()) {
            long courseId = entry.getKey();
            List<Long> lessonIdList = entry.getValue();
            courseLevelIdListMap.put(courseId, lessonIdList);
        }
    }
}
