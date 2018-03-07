package com.yanzi.pisces.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yanzi.common.entity.user.BillsInfo;
import com.yanzi.pisces.entity.CourseTermInfo;
import com.yanzi.pisces.entity.UserTermCourseEntity;

public interface UserCourseTermMapper {
    public List<UserTermCourseEntity> selectUserCourseTermByUserId(@Param(value = "userId") long userId);

   
    public long selectUserTermIdByUserIdAndCourseId(@Param(value = "userId") long userId, @Param(value = "courseId") long courseId);
    
    /**
     * 用户购买学期
     * @param userId
     * @param termId
     * @author dusc
     */
    public void userPurchaseTerm(@Param(value="userId")long userId,@Param(value="courseId")long courseId,@Param(value="termId")long termId);
    
    /**
     * 增加雁币
     * @param userId
     * @param coins
     * @author dusc
     */
    public void addCoins(@Param(value="userId") long userId,@Param(value="coins") double coins);
    
    /**
     * 减少雁币
     * @param userId
     * @param coins
     * @author dusc
     */
    public void reduceCoins(@Param(value="userId") long userId,@Param(value="coins") double coins);
    
    /**
     * 查询用户相关课程
     * @param userId
     * @return
     */
	public List<CourseTermInfo> getCourseTermInfoByUserId(Long userId);
	
	public void userPurchase(@Param(value="userId")long userId,@Param(value="courseId")long courseId,@Param(value="termId")long termId,@Param(value="coins")double coins);
	
	public List<BillsInfo> checkPurchase(@Param(value="userId")long userId,@Param(value="courseId")long courseId,@Param(value="termId")long termId);
	
	public long loadLatestLesson(@Param(value="termId") long termId);
	
	public List<Long> getUserId(@Param(value="courseId")long courseId,@Param(value="termId")long termId);


	public List<Long> getUserByCourseIdTermId(@Param(value="courseId")long courseId,@Param(value="termId")long termId);
}