package com.yanzi.taurus.service;

import java.util.List;

import com.yanzi.common.service.CUserFriendService;

public interface UserFriendService extends CUserFriendService{

	
     public List<Long> getFriendUserIds(long userId, int pageId, int limit);	

}
