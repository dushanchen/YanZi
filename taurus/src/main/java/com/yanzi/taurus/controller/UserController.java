package com.yanzi.taurus.controller;

import java.security.interfaces.RSAPrivateKey;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.common.utils.RSAEncrypt;
import com.yanzi.taurus.controller.params.AddFeedbackParams;
import com.yanzi.taurus.controller.params.BindingPhoneNoParams;
import com.yanzi.taurus.controller.params.BindingThirdPartyParams;
import com.yanzi.taurus.controller.params.FetchFriendsParams;
import com.yanzi.taurus.controller.params.ModifyPasswordParams;
import com.yanzi.taurus.controller.params.ModifyPhoneNoParams;
import com.yanzi.taurus.controller.params.RaiseAppDurationParams;
import com.yanzi.taurus.controller.params.RegisterParams;
import com.yanzi.taurus.controller.params.ResetPasswordParams;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.service.LoginService;
import com.yanzi.taurus.service.RegisterService;
import com.yanzi.taurus.service.UserService; 
import com.yanzi.taurus.view.ViewDurationResponse;
import com.yanzi.taurus.view.ViewFeedbackResponse;
import com.yanzi.taurus.view.ViewFriengListResponse;
import com.yanzi.taurus.view.ViewUserNoResponse;
import com.yanzi.taurus.view.ViewUserResponseBase;

@Controller
public class UserController extends BaseController<ViewResponseBase> implements InitializingBean {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ParamsUtils paramsUtils;

    private RSAPrivateKey privateKey;

//    @RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
//    @ResponseBody
//    public ResponseEntity<ResponseEntityWrapper> registerByPhoneNo(@Valid RegisterParams params) {
//        long userId = registerService.registerByPhoneNo(params.getPhoneNo(), params.getPassword(),
//                params.getVerifiCode(), params.getNickName());
//        return packageSuccessData(new ViewUserResponseBase(userId));
//    }
    /**
     * 注册第一步
     * @param params
     * @return
     * @author dusc
     */
    @RequestMapping(value = "/registerFirst", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> registerByPhoneNo(@Valid RegisterParams params) {
        long userId = registerService.registerByPhoneNo(params.getPhoneNo(),
                params.getVerifiCode(), params.getNickName());
        return packageSuccessData(new ViewUserResponseBase(userId));
    }
    /**
     * 注册第二步
     * @param params
     * @return
     * @author dusc
     */
    @RequestMapping(value = "/registerSecond", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> registerSecond(@Valid RegisterParams params) {
        long userId = params.getUserId();
        String password = params.getPassword();
        registerService.savePassword(userId, password);
        return packageSuccessData(new ViewUserResponseBase(userId));
    }
    
    @RequestMapping(value = "/reset/password", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> resetPassword(@Valid ResetPasswordParams params) {
        long userId = userService.resetPassword(params.getPhoneNo(), params.getPassword(),
                params.getVerifiCode());
        return packageSuccessData(new ViewUserResponseBase(userId));
    }

    @RequestMapping(value = "/modify/phoneno", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> modifyPhoneNo(@Valid ModifyPhoneNoParams params) {
        long userId = userService.loadUserId(params.getToken());
        userService.modifyPhoneNo(userId, params.getPhoneNo(),
                params.getVerifiCode());
        return packageSuccessData(new ViewUserResponseBase(userId));
    }

    @RequestMapping(value = "/modify/password", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> modifyPassword(
            @Valid ModifyPasswordParams params) {
        long userId = paramsUtils.getUserId(params);
        userService.modifyPassword(userId, params.getNewPassword(),
                params.getOldPassword());
        return packageSuccessData(new ViewUserResponseBase(userId));
    }

    @RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> logout(@Valid UserActionParamsBase params) {
        loginService.logout(params.getToken());
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }

    @RequestMapping(value = "/binding/thirdparty", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> bindingThirdParty(
            @Valid BindingThirdPartyParams params) {
        String loginStr = new String(
                RSAEncrypt.decrypt(privateKey, Base64.decodeBase64(params.getParam())));
        ThirdPartyInfo thirdPartyInfo = JSON.parseObject(loginStr, ThirdPartyInfo.class);
        long userId = paramsUtils.getUserId(params);
        userService.addUserThirdPartyInfo(userId, thirdPartyInfo);
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }

    @RequestMapping(value = "/binding/phoneno", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> bindingPhoneNo(
            @Valid BindingPhoneNoParams params) {
        long userId = paramsUtils.getUserId(params);
        userService.bindingPhoneNo(userId, params.getPhoneNo(), params.getVerifiCode(),
                params.getPassword());
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }
    /**
     * 获取用户在线总时长
     * @param params
     * @return
     * @author dusc
     */
    @RequestMapping(value = "/get/app/duration", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> getUserAppDuration(
            @Valid RaiseAppDurationParams params) {
        long userId = paramsUtils.getUserId(params);
        long duration = userService.loadAppDuration(userId);
        ViewDurationResponse response = new ViewDurationResponse();
        response.setDuration(duration);
        return packageSuccessData(response);
    }
    /**
     * 增加用户在线时长
     * @param params
     * @return
     * @author dusc
     */
    @RequestMapping(value = "/raise/app/duration", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> raiseUserAppDuration(
            @Valid RaiseAppDurationParams params) {
        long userId = paramsUtils.getUserId(params);
        userService.addAppDuration(userId, params.getDuration());
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }

    @RequestMapping(value = "/load/usernoinfo", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadUserNoInfo(
            @Valid UserActionParamsBase params) {
        long userId = paramsUtils.getUserId(params);
        ViewUserNoResponse resonse = new ViewUserNoResponse();
        AccountInfo accountInfo = userService.getAccountInfoByUserId(userId);
        List<ThirdPartyInfo> thirdPartyInfos = userService.getThirdPartyInfoByUserId(userId);
        resonse.setId(accountInfo.getId());
        resonse.setPhoneNo(accountInfo.getPhoneNo());
        resonse.setThirdPartyIds(thirdPartyInfos);
        return packageSuccessData(resonse);
    }
    /**
     * 加载用户留言
     * @param params
     * @return
     * @author dusc
     */
    @RequestMapping(value = "/load/feedback", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadUserFeedback(
            @Valid UserActionParamsBase params) {
        ViewFeedbackResponse response = new ViewFeedbackResponse();
        List<FeedbackInfo> feedbacks = userService.loadUserFeedback(params.getToken());
        response.setFeedbacks(feedbacks);
        return packageSuccessData(response);
    }
    /**
     * 添加留言
     * @param params
     * @return
     * @author dusc
     */
	  @RequestMapping(value = "/add/feedback", method = { RequestMethod.GET, RequestMethod.POST })
	  @ResponseBody
	  public ResponseEntity<ResponseEntityWrapper> loadUserFeedback(
	          @Valid AddFeedbackParams params) {
	      String message = params.getMessage();
	      long userId = paramsUtils.getUserId(params);
	      userService.addUserFeedback(userId, message);
	      return packageSuccessData(new ViewResponseBase());
	  }
	  /**
	   * 模糊查询用户的好友
	   * @param params
	   * @return
	   * @author dusc
	   */
	  @RequestMapping(value = "/fetch/friends", method = { RequestMethod.GET, RequestMethod.POST })
	  @ResponseBody
	  public ResponseEntity<ResponseEntityWrapper> fetchFriends(
	          @Valid FetchFriendsParams params) {
	      String nickName = params.getNickName();
	      long userId = paramsUtils.getUserId(params);
	      
	      ViewFriengListResponse response = new ViewFriengListResponse();
	      response.setFriends(userService.fetchFriends(userId, nickName));
	      return packageSuccessData(response);
	  }
	  
	  
    @Override
    public void afterPropertiesSet() throws Exception {
        privateKey = RSAEncrypt.loadPrivateKeyByStr(
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVEdd3fjuFzjG1/1kX8qXpuIQV9KpTn1rtnFQiV51p3qytp2iB8Z+teFoN/z+4yg6aS3EB/1vHrIGVxtLlU/+T4+NDh5h5N/dlRuOV3iiNK/Et8xMrtejwvq9PuqX65yq8J2v9hLlBnQkS8kKhP/BY+51B+6fVbhX43Ay1dKlVPAgMBAAECgYBpUhCfPcoLaRyz54UBAvxqdmZ63gJV9M1GjnG8D/PpFlwyBXopu75qI4LLeJdlIDH/5JWSUSYE86eonmbiuQV9tSt/Hd7mpvxW+AXreK+ox6ztiYMg10tq/8K8UpvGjkMrRVUHb/STRUY8r3Kv/ZeFXJmxcOpNsVz3f2iQlU2FiQJBAP21mktp0XzlKIXnZNgOhvAV/TUmWPWUkFbxtO//lzYOcnkvgW0EndGkUI/ua1mCTVrvib0ojtn1mTh3VuUsJNUCQQCmwnE1XOt2SyfEqOW51swHToyp2MWMT1rbIBRIzL/vmsKKmY1hw9d8M2qnb8oU03dazQKoNYoCqhfOTOAP7/OTAkEApkkCqe7fOObRWoJA3EMZOf6PiOhrYfpPaEzfdHWm2+04Jil2wMdH0QHLM6rmfTIkFTfupSYSCtUn6ZR+RZJbSQJAEup3gQAbTX3U8v/dnyj4V9PXLOUD85iEy9plsqRXGUzKyIIGgZJ/fP0wGfIaUCZ0oX4j0QTRtN+qd6JMwEINtQJAFGMK7tBMRvW8tIW1F87NfLkBm8ygdkR+JWm+fdXT1o8D+97/ltOxIrE42CTef8N7UCK0axaXoA9RhKCi9R526w==");
    }
}
