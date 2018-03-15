package com.yanzi.taurus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.taurus.controller.params.SMSRegisterParams;
import com.yanzi.taurus.controller.params.SMSResetPasswordParams;
import com.yanzi.taurus.controller.params.SMSResetPhoneNoParams;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.service.impl.SMSServiceImpl;
import com.yanzi.taurus.view.ViewSMSResponse;

@Controller
public class SMSController extends BaseController<ViewSMSResponse> {

    @Autowired
    private SMSServiceImpl smsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sms/verifi/register", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> smsRegister(@Valid SMSRegisterParams params) {
        long id = smsService.sendVerifiMessageRegister(params.getPhoneNo());
        ViewSMSResponse response = new ViewSMSResponse(id);
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/sms/verifi/resetpassword", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> smsResetPassword(
            @Valid SMSResetPasswordParams params) {
        long id = smsService.sendVerifiMessageResetPassword(params.getPhoneNo());
        ViewSMSResponse response = new ViewSMSResponse(id);
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/sms/verifi/modifyphoneno", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> smsModifyPhoneNo(
            @Valid SMSResetPhoneNoParams params) {
        long userId = userService.loadUserId(params.getToken());
        long id = smsService.sendVerifiMessageModifyPhoneNo(params.getPhoneNo(), userId);
        ViewSMSResponse response = new ViewSMSResponse(id);
        return packageSuccessData(response);
    }
}
