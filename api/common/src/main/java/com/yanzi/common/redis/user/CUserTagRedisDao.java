package com.yanzi.common.redis.user;

import java.util.List;

import com.yanzi.common.entity.user.TagInfo;

public interface CUserTagRedisDao {

    /**
     * 根据ID获取标签信息
     * 
     * @param tagIds
     * @return
     */
    public List<TagInfo> getTagsByIds(List<Long> tagIds);

    /**
     * 新增标签信息
     * 
     * @param tagId
     * @param tagInfo
     */
    public void addTag(long tagId, TagInfo tagInfo);
}
