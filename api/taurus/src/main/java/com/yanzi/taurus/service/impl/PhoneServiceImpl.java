package com.yanzi.taurus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.exception.CommonException;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public AccountInfo isRegisted(String phoneNo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByPhoneNo(phoneNo);
        if (null == accountInfo) {
            throw new CommonException(ReturnCode.PHONE_NO_IS_NOT_REGISTER);
        }
        return accountInfo;
    }

    public void isNotRegisted(String phoneNo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByPhoneNo(phoneNo);
        if (null != accountInfo) {
            throw new CommonException(ReturnCode.PHONE_NO_IS_REGISTER);
        }
    }
}
