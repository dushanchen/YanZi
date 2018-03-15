package com.yanzi.taurus.service.impl;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yanzi.taurus.view.ViewImageUploadResponse;
import com.yanzi.taurus.entity.ImageInfo;
import com.yanzi.taurus.enums.ImageUploadSource;
import com.yanzi.taurus.mysql.ImageMapper;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.ImageService;
 

@Service
public class ImageServiceImpl implements ImageService {
	
	private static final String ACCESS_KEY = "bMsxBlEhchY7vyVNBn8aZRMbQWNIITXd2oewsiQA";
    private static final String SECRET_KEY = "B2hDo4x-wTlXcKfOUI9wnA3wrn9iGkqHtTV3eoQY";
    private static final String URL_PREFIX = "http://qiniu.image.yetter.cn/";
    private static final int TOKEN_EXPRIES = 5 * 60;
    private static final String BUCKET = "yetter";

    private Auth auth;

//    @Autowired
//    private ImageMapper imageMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }

    public ViewImageUploadResponse getUploadInfo(ImageUploadSource source, long userId) {
        String imageName = "";
        if(0 != userId) {
            imageName = source.getPrefix() + userId + "/" + System.currentTimeMillis();
        } else {
            imageName = source.getPrefix() + System.currentTimeMillis();
        }
        String imageUrl = URL_PREFIX + imageName;
        // insert mysql
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setSource(source.getSource());
        imageInfo.setUrl(imageUrl);
//        imageMapper.add(imageInfo);

        String token = auth.uploadToken(BUCKET, imageName, TOKEN_EXPRIES, new StringMap());
        return new ViewImageUploadResponse(token, imageName, URL_PREFIX);
    }

}
