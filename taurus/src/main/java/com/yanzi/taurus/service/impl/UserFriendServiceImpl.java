package com.yanzi.taurus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.service.CUserFriendService;
import com.yanzi.common.service.impl.CUserFriendServiceImpl;
import com.yanzi.common.utils.PageIndexCalUtil;
import com.yanzi.taurus.mysql.UserFriendMapper;
import com.yanzi.taurus.service.UserFriendService;

@Service
public class UserFriendServiceImpl extends CUserFriendServiceImpl implements UserFriendService {

	

	
    @Autowired
    private UserFriendMapper userFriendMapper;
    
    @Autowired
    private CUserFriendService cUserFriendService;

    public void addFriend(long userId, List<Long> friendIds) {
        userFriendMapper.addFriend(userId, friendIds);
        super.addFriend(userId, friendIds);
    }

    public void removeFriend(long userId, List<Long> friendIds) {
        userFriendMapper.removeFriend(userId, friendIds);
        super.removeFriend(userId, friendIds);
    }

  /**
   * @author zjy
   * 分页获取userid
   * @param userId
   * @param pageId
   * @param limit
   * @return
   */
	@Override
	public List<Long> getFansUserIds(long userId, int pageId, int limit) {
		  long start = PageIndexCalUtil.getIndexBegin(pageId, limit);
	      long end = start + limit -1;
	      List<Long> userIds = cUserFriendService.getFansIds(userId, start, end);
	      return userIds;
	}

@Override
public List<Long> getFriendUserIds(long userId, int pageId, int limit) {
	 long start = PageIndexCalUtil.getIndexBegin(pageId, limit);
     long end = start + limit -1;
     List<Long> userIds = cUserFriendService.getFriendIds(userId, start, end);
     return userIds;
}
    
}
