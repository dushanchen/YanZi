package com.yanzi.taurus.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import com.yanzi.common.entity.user.BillsInfo;
import com.yanzi.common.entity.user.PermissionInfo;
import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.taurus.controller.params.LoadUserPersonalCenterParam;
import com.yanzi.taurus.controller.params.ModifyBasicInfoParams;
import com.yanzi.taurus.entity.DialogInfo;
import com.yanzi.taurus.entity.UserCourseInfo;
import com.yanzi.taurus.entity.base.FriendInfo;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.view.ViewBasicInfoResponse;
import com.yanzi.taurus.view.ViewUserPersonalCenterResponse;

@Controller
public class BasicController extends BaseController<ViewResponseBase> {

    @Autowired
    private UserService userService;
    @Autowired
    private ParamsUtils paramsUtils;

    @RequestMapping(value = "/modify/userinfo", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> modifyBasicInfo(
            @Valid ModifyBasicInfoParams params) {
        long userId = paramsUtils.getUserId(params);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(params, userInfo);
        userInfo.setId(userId);
        userService.saveUserInfo(userInfo);
        return packageSuccessData(new ViewResponseBase());
    }

    @RequestMapping(value = "/load/basicinfo", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    //@Deprecated
    public ResponseEntity<ResponseEntityWrapper> loadBasicInfo(@Valid UserActionParamsBase params) {
        long userId = paramsUtils.getUserId(params);
        
        ViewBasicInfoResponse response = new ViewBasicInfoResponse();
        UserInfo userInfo = userService.loadUserInfo(userId);
        response.setBasicInfo(userInfo);
 
        UserCourseInfo userCourseInfo = userService.loadUserCourseInfo(userId);
        response.setUserCourseInfo(userCourseInfo);
        
        long userAppDuration = userService.loadUserAppDuration(userId);
        response.setUserAppDuration(userAppDuration);
        
        FriendInfo friendInfo = new FriendInfo();
        long friendCount = userService.getFriendCount(userId);
        friendInfo.setFriendCount(friendCount);
        long fansCount = userService.getFansCount(userId);
        friendInfo.setFansCount(fansCount);
        response.setUserFriendInfo(friendInfo);
        
        PermissionInfo permissionInfo = userService.loadPermissionInfo(userId);
        response.setPermissionInfo(permissionInfo);
        
        double gottenCoins=userService.getGottenCoins(userId);
        response.setGottenCoins(gottenCoins);
        
        return packageSuccessData(response);
    }
    
//    @RequestMapping(value = "/modify/basicinfo", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    //@Deprecated
//    public ResponseEntity<ResponseEntityWrapper> modifyBasicinfo(@Valid UserActionParamsBase params) {
//    	long userId = paramsUtils.getUserId(params);
//        ViewUserPersonalCenterResponse response = new ViewUserPersonalCenterResponse();
//        
//        return packageSuccessData(response);
//    }
}
