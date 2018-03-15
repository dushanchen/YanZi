package com.yanzi.taurus.service.impl;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.trace.MLogger;
import com.yanzi.taurus.mysql.InviteCodeMapper;
import com.yanzi.taurus.service.InviteCodeService;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(InviteCodeServiceImpl.class);

    private InviteCodeMapper inviteCodeMapper;

    @Override
    public void judge(String inviteCode) {
        // get verifi Code
        int count = inviteCodeMapper.selectInviteCodeCount(inviteCode);
        if (count == 0) {
            throw new CommonException(ReturnCode.INVITE_CODE_IS_ERROR);
        }
    }

    @Override
    public void use(String inviteCode) {
        // set code invalid
        inviteCodeMapper.updateInvalid(inviteCode);
    }
}
