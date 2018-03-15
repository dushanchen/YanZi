package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.TagInfo;
/**
 * 用户在线时长
 * @author dusc 
 *
 */
public class ViewDurationResponse extends ViewResponseBase {
	/**
	 * 用户在线时长
	 */
    private long duration;

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}
    
    
}
