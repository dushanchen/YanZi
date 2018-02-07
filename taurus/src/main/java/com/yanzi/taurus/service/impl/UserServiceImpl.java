package com.yanzi.taurus.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.LoadingCache;
import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.entity.user.TagInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.exception.CommonException;
import com.yanzi.common.redis.user.CUserRedisDao;
import com.yanzi.common.service.impl.CUserServiceImpl;
import com.yanzi.common.utils.HttpClientUtils;
import com.yanzi.common.utils.YanziResponseUtils;
import com.yanzi.taurus.entity.AccountInfo;
import com.yanzi.taurus.entity.FeedbackInfo;
import com.yanzi.taurus.entity.ThirdPartyInfo;
import com.yanzi.taurus.entity.UserCourseInfo;
import com.yanzi.taurus.enums.SMSVerifiCodeType;
import com.yanzi.taurus.mysql.UserMapper;
import com.yanzi.taurus.service.PhoneService;
import com.yanzi.taurus.service.UserService;

@Service
public class UserServiceImpl extends CUserServiceImpl implements UserService{
	@Value("#{configProperties['taurus.user.tag.url']}")
    private String loadUserTagUrl = "";
	@Value("#{configProperties['taurus.user.course.level.url']}")
	    private String loadUserCourseLevelUrl = "";
	@Value("#{configProperties['pisces.course.url']}")
    private String loadAllCourseUrl = "";
	
    @Autowired
    private SMSServiceImpl smsService;
    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CUserRedisDao cUserRedisDao;
    @Autowired
    private HttpClientUtils httpClientUtils;

    private LoadingCache<String, Long> userTokenToIdCache;
    
    @Override
    public void saveUserInfo(UserInfo userInfo) {
        super.saveUserInfo(userInfo);
        userMapper.insertOrUpdateUserInfo(userInfo);
    }

    @Override
    public long resetPassword(String phoneNo, String password, String userVerifiCode) {
        // judge phone is register
        AccountInfo accountInfo = phoneService.isRegisted(phoneNo);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PASSWORD);
        accountInfo.setPassword(password);
        userMapper.updateAccountInfo(accountInfo);
        return accountInfo.getId();
    }
    
    @Override
    public void modifyPhoneNo(long userId, String phoneNo, String userVerifiCode) {
        phoneService.isNotRegisted(phoneNo);
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PHONENO);
        accountInfo.setPhoneNo(phoneNo);
        userMapper.updateAccountInfo(accountInfo);
    }
    
    @Override
    public void modifyPassword(long userId, String newPassword, String oldPassword) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        if (!accountInfo.getPassword().equals(oldPassword)) {
            throw new CommonException(ReturnCode.PASSWORD_ERROR);
        }
        accountInfo.setPassword(newPassword);
        userMapper.updateAccountInfo(accountInfo);
    }

    @Override
    public void addUserThirdPartyInfo(long userId, ThirdPartyInfo thirdPartyInfo) {
        AccountInfo accountInfo = userMapper.selectAccountInfoByThirdPartyInfo(thirdPartyInfo);
        if (null != accountInfo) {
            throw new CommonException(ReturnCode.THIRD_PARTY_ID_IS_BINDED);
        }
        thirdPartyInfo.setUserId(userId);
        userMapper.insertOrUpdateThirdPartyInfo(thirdPartyInfo);
    }
    
    @Override
    public void bindingPhoneNo(long userId, String phoneNo, String userVerifiCode,
            String password) {
        phoneService.isNotRegisted(phoneNo);
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        smsService.verificationCodeJudge(phoneNo, userVerifiCode, SMSVerifiCodeType.RESET_PHONENO);
        accountInfo.setPhoneNo(phoneNo);
        accountInfo.setPassword(password);
        userMapper.updateAccountInfo(accountInfo);
    }
    
    @Override
    public AccountInfo getAccountInfoByUserId(long userId){
        AccountInfo accountInfo = userMapper.selectAccountInfoByUserId(userId);
        if(null == accountInfo) {
            return AccountInfo.DEFAULT;
        }
        return accountInfo;
    }

    @Override
    public List<ThirdPartyInfo> getThirdPartyInfoByUserId(long userId) {
        List<ThirdPartyInfo> thirdPartyInfos = userMapper.selectThirdPartyInfoByUserId(userId);
        if(null == thirdPartyInfos) {
            return Collections.emptyList();
        }
        return thirdPartyInfos;
    }

    /**
     * 根据token获取userId
     * 
     * @param token
     * @return userId
     */
    public long getUserIdByToken(String token) {
        long userId = getUserIdByTokenNoException(token);
        if (0 == userId) {
            throw new CommonException(ReturnCode.TOKEN_IS_NOT_EXIST);
        }
        return userId;
    }
    
    private long getUserIdByTokenNoException(String token) {
        try {
            return userTokenToIdCache.get(token);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取用户基本信息
     * 
     * @param userId
     * @return
     */
    public UserInfo getUserInfoById(long userId) {
    	return cUserRedisDao.getUserInfoById(userId);
    }
//    public List<FeedbackInfo> loadUserFeedback(String token) {
//        long userId = geminiUserService.getUserIdByToken(token);
//        return userMapper.selectFeedbackByUserId(userId);
//    }
    
    //用户课程关系引入
    public UserCourseInfo loadUserCourseInfo(long userId) {
        long exp = cUserRedisDao.getCourseExpV2(userId);
        long knowledge = cUserRedisDao.getCourseKnowledgeV2(userId);
        UserCourseInfo userCourseInfo = new UserCourseInfo();
        userCourseInfo.setExp(exp);
        userCourseInfo.setKnowledge(knowledge);
//        List<CourseInfo> allcourseList = loadAllCourse();
//        Map<Long, CourseInfo> courseMap = new HashMap<>();
//        if (null != allcourseList && !allcourseList.isEmpty()) {
//            for (CourseInfo courseInfo : allcourseList)
//            	courseMap.put(courseInfo.getId(), courseInfo);
//        }
//        List<LevelInfo> courseLevels = loadUserCourseLevel(userId);
//        for (LevelInfo levelInfo : courseLevels) {
//        	CourseInfo courseInfo = courseMap.get(levelInfo.getCourseId());
//            if (null != courseInfo) {
//                levelInfo.setCourseTitle(courseInfo.getTitle());
//            }
//        }
//        userCourseInfo.setCourseLevels(courseLevels);
        return userCourseInfo;
    }
    //获取所有的课程
    public List<CourseInfo> loadAllCourse() {
        String url = loadAllCourseUrl;
        String response = httpClientUtils.getResponse(url);
        JSONObject responseJson = JSON.parseObject(response);
        if (!YanziResponseUtils.isValid(responseJson)) {
            return Collections.emptyList();
        }
        JSONObject message = YanziResponseUtils.getMessage(responseJson);
        JSONArray courseJsonArray = message.getJSONArray("course");
        if (null == courseJsonArray || courseJsonArray.isEmpty()) {
            return Collections.emptyList();
        }
        List<CourseInfo> result = new ArrayList<>();
        for (int i = 0; i < courseJsonArray.size(); ++i) {
            String courseStr = courseJsonArray.getString(i);
            if (StringUtils.isEmpty(courseStr)) {
                continue;
            }
            CourseInfo courseInfo = JSON.parseObject(courseStr, CourseInfo.class);
            result.add(courseInfo);
        }
        return result;
    }
    //获取用户课程相关等级
    public List<LevelInfo> loadUserCourseLevel(long userId) {
        String url = String.format(loadUserCourseLevelUrl, userId);
        String response = httpClientUtils.getResponse(url);
        JSONObject responseJson = JSON.parseObject(response);
        if (!YanziResponseUtils.isValid(responseJson)) {
            return Collections.emptyList();
        }
        JSONObject message = YanziResponseUtils.getMessage(responseJson);
        JSONArray levelJsonArray = message.getJSONArray("levels");
        if (null == levelJsonArray || levelJsonArray.isEmpty()) {
            return Collections.emptyList();
        }

        List<LevelInfo> result = new ArrayList<>();
        for (int i = 0; i < levelJsonArray.size(); ++i) {
            String levelStr = levelJsonArray.getString(i);
            if (StringUtils.isEmpty(levelStr)) {
                continue;
            }
            LevelInfo level = JSON.parseObject(levelStr, LevelInfo.class);
            result.add(level);
        }
        return result;
    }
    
    public List<TagInfo> loadUserTag(long userId) {
        String url = String.format(loadUserTagUrl, userId);
        String response = httpClientUtils.getResponse(url);
        JSONObject responseJson = JSON.parseObject(response);
        if (!YanziResponseUtils.isValid(responseJson)) {
            return Collections.emptyList();
        }
        JSONObject message = YanziResponseUtils.getMessage(responseJson);
        JSONArray tagJsonArray = message.getJSONArray("followedTags");
        if (null == tagJsonArray || tagJsonArray.isEmpty()) {
            return Collections.emptyList();
        }

        List<TagInfo> result = new ArrayList<>();
        for (int i = 0; i < tagJsonArray.size(); ++i) {
            String tagStr = tagJsonArray.getString(i);
            if (StringUtils.isEmpty(tagStr)) {
                continue;
            }
            TagInfo tag = JSON.parseObject(tagStr, TagInfo.class);
            result.add(tag);
        }
        return result;
    }
    //用户在线时长
	@Override
	public long loadUserAppDuration(long userId) {
		return cUserRedisDao.getAppDuration(userId);
	}
	//获取关注数量
	@Override
	public long getFriendCount(long userId) {
		// TODO Auto-generated method stub
		return cUserRedisDao.getIdolCount(userId);
	}
    //获取粉丝数量
	@Override
	public long getFansCount(long userId) {
		// TODO Auto-generated method stub
		return cUserRedisDao.getFansCount(userId);
	}
	
    public List<FeedbackInfo> loadUserFeedback(String token) {
        long userId = loadUserId(token);
        return userMapper.selectFeedbackByUserId(userId);
    }
    /**
     * 添加留言
     * @author dusc
     * @param userId
     * @param message
     */
    public void addUserFeedback(long userId,String message){
    	userMapper.addFeedback(userId,message);
    }
}
