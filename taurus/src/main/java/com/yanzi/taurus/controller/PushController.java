package com.yanzi.taurus.controller;

import javax.validation.Valid;

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
import com.yanzi.common.entity.user.PushInfo;
import com.yanzi.taurus.controller.params.ModifyPushConfigParams;
import com.yanzi.taurus.service.PushService;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.view.ViewPushConfigResponse;

@Controller
public class PushController extends BaseController<ViewResponseBase> {

    @Autowired
    private PushService pushService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/load/pushconfig", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    @Deprecated
    public ResponseEntity<ResponseEntityWrapper> loadPushconfig(
            @Valid UserActionParamsBase params) {
        long userId = userService.loadUserId(params.getToken());
        PushInfo pushInfo = userService.loadPushInfo(userId);
        return packageSuccessData(new ViewPushConfigResponse(pushInfo));
    }

    @RequestMapping(value = "/modify/pushconfig", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> modifyPushconfig(
            @Valid ModifyPushConfigParams params) {
        PushInfo pushInfo = new PushInfo();
        BeanUtils.copyProperties(params, pushInfo);
        long userId = userService.loadUserId(params.getToken());
        pushInfo.setUserId(userId);
        pushService.setPushInfo(pushInfo);
        return packageSuccessData(new ViewResponseBase());
    }
}
