package com.yanzi.taurus.service;

import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface RegisterService {

    public long registerByPhoneNo(String phoneNo, String password, String userVerifiCode, String nickName);

    public long registerByThirdPartyId(ThirdPartyInfo thirdPartyInfo);

}
