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
     * @author dusc
     */
    public void userPurchaseTerm(@Param(value="userId") long userId,@Param(value="courseId") long courseId,@Param(value="termId") long termId);
    /**
     * 增加雁币
     * @param userId
     * @param coins
     * @author dusc
     */
    public void addCoins(@Param(value="userId") long userId,@Param(value="coins") long coins);
    
    /**
     * 减少雁币
     * @param userId
     * @param coins
     * @author dusc
     */
    public void reduceCoins(@Param(value="userId") long userId,@Param(value="coins") long coins);
}