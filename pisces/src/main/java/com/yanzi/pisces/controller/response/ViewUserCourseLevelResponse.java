package com.yanzi.pisces.controller.response;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.level.LevelInfo;


public class ViewUserCourseLevelResponse extends ViewResponseBase {

    private List<LevelInfo> levels;

    public List<LevelInfo> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelInfo> levels) {
        this.levels = levels;
    }
}
