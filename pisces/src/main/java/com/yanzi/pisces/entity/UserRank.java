package com.yanzi.pisces.entity;

import com.yanzi.common.entity.user.UserInfo;

public class UserRank {
    private UserInfo userInfo;
    private RankInfo rankInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public RankInfo getRankInfo() {
        return rankInfo;
    }

    public void setRankInfo(RankInfo rankInfo) {
        this.rankInfo = rankInfo;
    }
}
