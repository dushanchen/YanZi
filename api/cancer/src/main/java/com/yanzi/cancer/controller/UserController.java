package com.yanzi.cancer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.cancer.controller.params.LoadUserLikeDialogParams;
import com.yanzi.cancer.controller.params.LoadUserReplyDialogParams;
import com.yanzi.cancer.service.UserService;
import com.yanzi.cancer.view.ViewDialogsResponse;
import com.yanzi.gemini.controller.BaseController;
import com.yanzi.gemini.controller.converter.ResponseEntityWrapper;
import com.yanzi.gemini.view.ViewResponseBase;

@Controller("CancerUserController") 
public class UserController extends BaseController<ViewResponseBase> {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/load/userlikedialogs", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadUserLikeDialogs(
            @Valid LoadUserLikeDialogParams params) {
        ViewDialogsResponse response = userService.loadUserLikeDialogs(params.getUserId(),
                params.getPageId(), params.getLimit());
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/load/userreplydialogs", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadUserReplyDialogs(
            @Valid LoadUserReplyDialogParams params) {
        ViewDialogsResponse response = userService.loadUserReplyDialogs(params.getUserId(),
                params.getPageId(), params.getLimit());
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/load/userdialogs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadUserDialogs(
            @Valid LoadUserReplyDialogParams params) {
        ViewDialogsResponse response = userService.loadUserDialogs(params.getUserId(),
                params.getPageId(), params.getLimit());
        return packageSuccessData(response);
    }
}
