package com.yanzi.pisces.data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yanzi.common.entity.college.level.LevelInfo;

@Component
public class LevelData {
    private Map<Long, LevelInfo> levelInfoMap = new ConcurrentHashMap<>();

    @Autowired
    private CourseData courseData;

    public LevelInfo get(long id) {
        LevelInfo levelInfo = levelInfoMap.get(id);
        if(null == levelInfo) {
            return LevelInfo.DEFAULT;
        }
        return levelInfo;
    }

    public LevelInfo getByCourseIdAndExp(long courseId, long exp) {
        List<Long> levelIdList = courseData.getLevelIdList(courseId);
        LevelInfo result = LevelInfo.DEFAULT;
        for (long levelId : levelIdList) {
            LevelInfo level = levelInfoMap.get(levelId);
            if (null == level) {
                continue;
            }
            if (exp >= level.getMinExp() && level.getLevel() > result.getLevel()) {
                result = level;
            }
        }
        return result;
    }

    public Map<Long, LevelInfo> getLevelInfoMap() {
        return levelInfoMap;
    }

    public void setLevelInfoMap(Map<Long, LevelInfo> levelInfoMap) {
        this.levelInfoMap = levelInfoMap;
    }
}
