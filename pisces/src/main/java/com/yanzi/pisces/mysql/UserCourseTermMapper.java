package com.yanzi.pisces.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.pisces.entity.UserTermCourseEntity;

public interface UserCourseTermMapper {
    public List<UserTermCourseEntity> selectUserCourseTermByUserId(@Param(value = "userId") long userId);

    @Deprecated
    public Long selectUserTermIdByUserIdAndCourseId(@Param(value = "userId") long userId, @Param(value = "courseId") long courseId);
    
    /**
     * 用户购买学期
     * @param userId
     * @param termId
     */
    public void userPurchaseTerm(@Param(value="userId") long userId,@Param(value="termId") long termId);
}