package com.yanzi.taurus.mysql;

import org.apache.ibatis.annotations.Param;

public interface InviteCodeMapper {
    public int selectInviteCodeCount(@Param(value = "inviteCode") String inviteCode);

    public void updateInvalid(@Param(value = "inviteCode") String inviteCode);
}
