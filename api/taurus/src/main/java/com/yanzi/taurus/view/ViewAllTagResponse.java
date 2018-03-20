package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.TagInfo;

public class ViewAllTagResponse extends ViewResponseBase {

    public ViewAllTagResponse(List<TagInfo> tags) {
        this.tags = tags;
    }

    public List<TagInfo> getTags() {
        return tags;
    }

    public void setTags(List<TagInfo> tags) {
        this.tags = tags;
    }

    private List<TagInfo> tags;
}
