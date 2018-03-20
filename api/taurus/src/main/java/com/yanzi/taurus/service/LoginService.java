package com.yanzi.taurus.service;

import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.DeviceInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface LoginService {

    AccountInfo loginByPhoneNo(String phoneNo, String password, DeviceInfo deviceInfo);

    AccountInfo loginByUserId(long userId, DeviceInfo deviceInfo);

    AccountInfo loginByThirdPartyInfo(ThirdPartyInfo thirdPartyInfo, DeviceInfo deviceInfo);

    void logout(String token);
    
    void bindThirdPartInfo(ThirdPartyInfo thirdPartyInfo,long userId);
    
    ThirdPartyInfo loadUserIdByThirdPartInfo(String thirdPartyId, int source);
    
    ThirdPartyInfo checkThirdParty(String thirdPartyId, int source);
}
