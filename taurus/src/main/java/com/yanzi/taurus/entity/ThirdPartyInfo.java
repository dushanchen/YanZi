package com.yanzi.taurus.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yanzi.taurus.enums.UserSource;

public class ThirdPartyInfo {
    private long userId;

    private String thirdPartyId;

    @JSONField(serialize=false)
    private String nickName = "";

    private int source = UserSource.PHONE_NO.getSource();

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
