package com.yanzi.cancer.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.cancer.entity.DialogInfo;

public interface DialogMapper {
    public List<DialogInfo> selectTagsByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);
}
