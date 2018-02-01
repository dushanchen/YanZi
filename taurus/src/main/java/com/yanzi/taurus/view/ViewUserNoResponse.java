package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public class ViewUserNoResponse extends ViewResponseBase{
    private long id;
    private String phoneNo;
    private List<ThirdPartyInfo> thirdPartyIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<ThirdPartyInfo> getThirdPartyIds() {
        return thirdPartyIds;
    }

    public void setThirdPartyIds(List<ThirdPartyInfo> thirdPartyIds) {
        this.thirdPartyIds = thirdPartyIds;
    }
}
