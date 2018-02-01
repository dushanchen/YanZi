package com.yanzi.taurus.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.yanzi.common.entity.user.TagInfo;

@Component
public class TagData {
    private Map<Long, TagInfo> tagInfoMap = new ConcurrentHashMap<Long, TagInfo>();

    public TagInfo get(long id){
        TagInfo tagInfo = tagInfoMap.get(id);
        if(null == tagInfo) {
            return TagInfo.DEFAULT;
        }
        return tagInfo;
    }
    
    public List<TagInfo> getAllTags(){
        return new ArrayList<TagInfo>(tagInfoMap.values());
    }
    
    public boolean contains(long id) {
        return tagInfoMap.containsKey(id);
    }
    
    public Map<Long, TagInfo> getTagInfoMap() {
        return tagInfoMap;
    }

    public void setTagInfoMap(Map<Long, TagInfo> tagInfoMap) {
        this.tagInfoMap = tagInfoMap;
    }
}
