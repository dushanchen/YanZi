package com.yanzi.pisces.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.college.level.LevelInfo;

public interface LevelMapper {
    public List<LevelInfo> selectByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);
}
