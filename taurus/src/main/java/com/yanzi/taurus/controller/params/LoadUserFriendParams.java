package com.yanzi.taurus.controller.params;

public class LoadUserFriendParams {
	 private long userId;
	    
	    private int pageId;
	    
	    private int limit;

	    public long getUserId() {
	        return userId;
	    }

	    public void setUserId(long userId) {
	        this.userId = userId;
	    }

	    public int getPageId() {
	        return pageId;
	    }

	    public void setPageId(int pageId) {
	        this.pageId = pageId;
	    }

	    public int getLimit() {
	        return limit;
	    }

	    public void setLimit(int limit) {
	        this.limit = limit;
	    }
}
