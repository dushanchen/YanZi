package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.UserInfo;


public class ViewFriengListResponse extends ViewResponseBase {
    private List<UserInfo> friends;

	public List<UserInfo> getFriends() {
		return friends;
	}

	public void setFriends(List<UserInfo> friends) {
		this.friends = friends;
	}

    
}
