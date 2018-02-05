package com.yanzi.taurus.service;

import com.yanzi.taurus.entity.ThirdPartyInfo;

public interface RegisterService {

//    public long registerByPhoneNo(String phoneNo, String password, String userVerifiCode, String nickName);
	/**
	 * 
	 * @param phoneNo
	 * @param password
	 * @param userVerifiCode
	 * @param nickName
	 * @return
	 * @author dusc
	 */
    public long registerByPhoneNo(String phoneNo,String userVerifiCode, String nickName);
    public void savePassword(long userId,String password);
    public long registerByThirdPartyId(ThirdPartyInfo thirdPartyInfo);

}
