package com.yanzi.taurus.service;

import org.springframework.beans.factory.InitializingBean;

import com.yanzi.taurus.enums.ImageUploadSource;
import com.yanzi.taurus.view.ViewImageUploadResponse;

public interface ImageService extends InitializingBean{
	 public ViewImageUploadResponse getUploadInfo(ImageUploadSource source, long userId);
}
