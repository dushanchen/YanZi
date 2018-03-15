package com.yanzi.taurus.job.mysql;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.taurus.data.TagData;
import com.yanzi.taurus.mysql.TagMapper;

public class TagIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(TagIncrLoadJob.class);

    @Autowired
    private TagData tagData;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, TagInfo> tagInfoMap;

    @Override
    protected void beforeRun() {
        tagInfoMap = tagData.getTagInfoMap();
    }

    @Override
    protected void mysqlLoad() {
        List<TagInfo> result = tagMapper.selectTagsByRangTime(beginTime, endTime);
        if (null == result || result.isEmpty()) {
            return;
        }
        for (TagInfo tag : result) {
            if (envUtils.isNotValid(tag.getValid())) {
                tagInfoMap.remove(tag.getId());
            } else {
                tagInfoMap.put(tag.getId(), tag);
            }
        }
    }
}
