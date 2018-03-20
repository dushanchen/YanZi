package com.yanzi.taurus.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.taurus.controller.params.FollowTagParams;
import com.yanzi.taurus.data.TagData;
import com.yanzi.taurus.service.TagService;
import com.yanzi.taurus.view.ViewAllTagResponse;
import com.yanzi.taurus.view.ViewFollowedTagResponse;
import com.yanzi.taurus.view.ViewUserFollowTagsResponse;

@Controller
public class TagController extends BaseController<ViewResponseBase> {

    @Autowired
    private TagData tagData;
    @Autowired
    private TagService tagService;
    @Autowired
    private ParamsUtils paramsUtils;

    //绑定标签
    @RequestMapping(value = "/user/follow/tags", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> modifyBasicInfo(@Valid FollowTagParams params) {
        long userId = paramsUtils.getUserId(params);
        boolean flag = tagService.userFollowTags(userId, params.getTagIds());
        ViewUserFollowTagsResponse response = new ViewUserFollowTagsResponse();	
        if(!flag)
        	response.setMsg("标签已清空");
        else
        	response.setMsg("标签绑定成功");
        return packageSuccessData(response);
        
    }

    //获取自己的标签
    @RequestMapping(value = "/user/load/tags", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadFollowedTags(
            @Valid UserActionParamsBase params) {
        long userId = paramsUtils.getUserId(params);
        List<TagInfo> tags = tagService.loadUserFollowTags(userId);
        ViewFollowedTagResponse response = new ViewFollowedTagResponse();
        response.setTags(tags);
        return packageSuccessData(response);
    }

    // 获取所有标签
    @RequestMapping(value = "/load/alltags", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadAllTags(@Valid UserActionParamsBase params) {
        List<TagInfo> tags = tagData.getAllTags();
        return packageSuccessData(new ViewAllTagResponse(tags));
    }
    
    
}
