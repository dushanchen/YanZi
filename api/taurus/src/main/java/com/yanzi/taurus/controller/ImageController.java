package com.yanzi.taurus.controller;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.taurus.controller.params.ImageUploadParams;
import com.yanzi.taurus.service.ImageService;
import com.yanzi.taurus.view.ViewImageUploadResponse; 

@Controller
public class ImageController extends BaseController<ViewResponseBase> implements InitializingBean{

    @Autowired
    private ImageService imageService; 
    @Autowired
    private ParamsUtils paramsUtils;
    @ResponseBody
    @RequestMapping(value = "/load/imageuploadinfo", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> loadAllCurriculum(
            @Valid ImageUploadParams params) {
        long userId = 0;
        if (StringUtils.isNotEmpty(params.getToken())) {
        	paramsUtils.getUserId(params);
        }
        ViewImageUploadResponse response = imageService.getUploadInfo(params.getSource(),
                userId);
        return packageSuccessData(response);
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}