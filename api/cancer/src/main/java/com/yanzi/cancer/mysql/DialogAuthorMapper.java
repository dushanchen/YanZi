package com.yanzi.cancer.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.cancer.entity.DialogAuthor;

public interface DialogAuthorMapper {
    public List<DialogAuthor> selectTagsByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);
}
