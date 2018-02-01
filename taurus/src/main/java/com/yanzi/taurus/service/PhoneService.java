package com.yanzi.taurus.service;

import com.yanzi.taurus.entity.AccountInfo;

public interface PhoneService {
    public AccountInfo isRegisted(String phoneNo);

    public void isNotRegisted(String phoneNo);
}
