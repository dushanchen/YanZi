package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.taurus.entity.DialogInfo;
import com.yanzi.taurus.entity.UserCourseInfo;
import com.yanzi.taurus.entity.base.FriendInfo;

public class ViewUserPersonalCenterResponse extends ViewResponseBase {
//    // 标签
//    private List<TagInfo> followedTags;
    // 基本信息
    private UserInfo userInfo;

    // 课程信息
    private UserCourseInfo userCourseInfo;

    // 驻留时间
    private long userAppDuration;
    
    private FriendInfo userFriendInfo;

//    public List<TagInfo> getFollowedTags() {
//        return followedTags;
//    }
//
//    public void setFollowedTags(List<TagInfo> followedTags) {
//        this.followedTags = followedTags;
//    }

    public UserInfo getUserBasicInfo() {
        return userInfo;
    }

    public void setUserBasicInfo(UserInfo userBasicInfo) {
        this.userInfo = userBasicInfo;
    }

    public UserCourseInfo getUserCourseInfo() {
        return userCourseInfo;
    }

    public void setUserCourseInfo(UserCourseInfo userCourseInfo) {
        this.userCourseInfo = userCourseInfo;
    }

    public long getUserAppDuration() {
        return userAppDuration;
    }

    public void setUserAppDuration(long userAppDuration) {
        this.userAppDuration = userAppDuration;
    }

    public FriendInfo getUserFriendInfo() {
        return userFriendInfo;
    }

    public void setUserFriendInfo(FriendInfo userFriendInfo) {
        this.userFriendInfo = userFriendInfo;
    }
}
