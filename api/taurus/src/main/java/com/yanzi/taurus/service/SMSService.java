package com.yanzi.taurus.service;

import com.yanzi.taurus.enums.SMSVerifiCodeType;

public interface SMSService {

    public void verificationCodeJudge(String phoneNo, String userVerifiCode, SMSVerifiCodeType type);

    public long sendVerifiMessageRegister(String phoneNo);

    public long sendVerifiMessageModifyPhoneNo(String phoneNo, long userId);

    long sendVerifiMessageResetPassword(String phoneNo);

}
