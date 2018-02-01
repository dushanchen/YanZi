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
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.utils.ArrayToListUtil;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.taurus.controller.params.FriendAddParams;import com.yanzi.taurus.controller.params.FriendJudgeParams;
import com.yanzi.taurus.controller.params.FriendRemoveParams;
import com.yanzi.taurus.service.UserFriendService;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.view.ViewFriendJudgeResponse;

@Controller
public class UserFriendController extends BaseController<ViewResponseBase> {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private ParamsUtils paramsUtils;


    @RequestMapping(value = "/add/friend", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> addUserFriend(@Valid FriendAddParams params) {
        long userId = paramsUtils.getUserId(params);
        userFriendService.addFriend(userId, ArrayToListUtil.parse(params.getFriendIds()));
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }

    @RequestMapping(value = "/remove/friend", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> removeUserFriend(
            @Valid FriendRemoveParams params) {
        long userId = paramsUtils.getUserId(params);
        userFriendService.removeFriend(userId, ArrayToListUtil.parse(params.getFriendIds()));
        return packageSuccessData(ViewResponseBase.DEFAULT_INSTANCE);
    }

    @RequestMapping(value = "/judge/friend", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> judgeIsFriend(@Valid FriendJudgeParams params) {
        long userId = paramsUtils.getUserId(params);
        List<Boolean> isFriends = userFriendService.judgeIsFriend(userId,
                ArrayToListUtil.parse(params.getFriendIds()));
        return packageSuccessData(new ViewFriendJudgeResponse(isFriends));
    }
}
