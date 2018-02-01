package com.yanzi.taurus.mysql;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.yanzi.taurus.entity.SMSVerifiCodeInfo;

public interface SMSVerifiCodeMapper {
    public SMSVerifiCodeInfo selectByPhoneNoAndType(@Param(value = "phoneNo") String phoneNo,
            @Param(value = "type") int type);

    public int selectCountByPhoneNoAndRangeTime(@Param(value = "phoneNo") String phoneNo,
            @Param(value = "beginTime") Timestamp beginTime,
            @Param(value = "endTime") Timestamp endTime);

    public int addNewRecord(@Param(value = "verifiCode") SMSVerifiCodeInfo verifiCode);

    public void updateInvalid(@Param(value = "id") long id);
}
