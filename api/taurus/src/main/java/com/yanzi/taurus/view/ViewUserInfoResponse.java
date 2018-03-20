package com.yanzi.taurus.view;
import java.util.List;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.UserInfo;


public class ViewUserInfoResponse extends ViewResponseBase {
    private List<UserInfo> users;

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}

  
}
