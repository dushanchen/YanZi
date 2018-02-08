package com.yanzi.taurus.mysql;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.user.TagInfo;

public interface TagMapper {
    public List<TagInfo> selectTagsByRangTime(@Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);
  
    public List<Long> selectTagIdsByUserId(@Param(value = "userId") long userId);

    public boolean insertOrUpdateTagIdsByUserId(@Param(value = "userId") long userId,
            @Param(value = "tagIds") long[] tagIds);

    public void deleteAllFollowedTags(@Param(value = "userId") long userId);
}
