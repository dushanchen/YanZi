package com.yanzi.pisces.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.common.entity.Date;
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.entity.user.UserInfo;
import com.yanzi.common.service.CUserFriendService;
import com.yanzi.common.service.impl.CUserCollegeServiceImpl;
import com.yanzi.common.utils.TimeUtils;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.LevelData;
import com.yanzi.pisces.data.TermData;
import com.yanzi.pisces.entity.CourseTermInfo;
import com.yanzi.pisces.entity.RankInfo;
import com.yanzi.pisces.entity.UserCollegeStatus;
import com.yanzi.pisces.entity.UserCourseTermStatus;
import com.yanzi.pisces.entity.UserLessonStatus;
import com.yanzi.pisces.entity.UserRank;
import com.yanzi.pisces.entity.UserTermCourseEntity;
import com.yanzi.pisces.entity.comparator.RankEntityCompartor;
import com.yanzi.pisces.mysql.CourseMapper;
import com.yanzi.pisces.mysql.LevelMapper;
import com.yanzi.pisces.mysql.UserCourseTermMapper;
import com.yanzi.pisces.service.UserCollegeService;
import com.yanzi.pisces.service.UserService;

@Service
public class UserCollegeServiceImpl extends CUserCollegeServiceImpl implements UserCollegeService {

    private static final int BASE_KNOWLEDGE_EXP = 2;
    private static final int DAY_COMPLETE_EXP = 3;
    // private static final int SEVEN_DAY_COMPLETE_EXP = 5;
    // private static final int SEVEN_DAY = 7;
    private static final int DAY_COMPLETE_KNOWLEDGE = 10;

    @Autowired
    private UserCourseTermMapper userCourseTermMapper;
    @Autowired
    private TermData termData;
    @Autowired
    private LessonData lessonData;
    @Autowired
    private LevelData levelData;
    @Autowired
    private CUserFriendService cUserFriendService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LevelMapper levelMapper;

    @Override
    public List<Long> loadCourseIdList(long userId) {
        List<UserTermCourseEntity> userCourseTermList = userCourseTermMapper
                .selectUserCourseTermByUserId(userId);
        List<Long> userCourseIdList = new ArrayList<>();
        for (UserTermCourseEntity entity : userCourseTermList) {
            if (termData.isValid(entity.getTermId())) {
                userCourseIdList.add(entity.getCourseId());
            }
        }
        return userCourseIdList;
    }

    @Override
    public long completeLesson(long userId, long courseId, long lessonId, long lessonKnowledge) {
        long termId = this.loadCourseTermId(userId, courseId);
        long lessonMaxKnowledge = this.loadCourseTermLessonMaxKnowledge(userId, courseId, termId,
                lessonId);
        
        
        
        long newKnowledge = lessonKnowledge - lessonMaxKnowledge;
        Date date = TimeUtils.getDate();
        // update lesson knowledge
        this.saveCourseTermLessonKnowledge(userId, courseId, termId, lessonId, lessonKnowledge);
        if (newKnowledge <= 0) {
            return 0;
        }
        // update lesson max knowledge
        this.saveCourseTermLessonMaxKnowledge(userId, courseId, termId, lessonId, lessonKnowledge);
        // update course knowledge
        long courseKnowledge = this.loadCourseTermKnowledge(userId, courseId, termId);
        courseKnowledge += newKnowledge;
        this.saveCourseTermKnowledge(userId, courseId, termId, courseKnowledge);
        // update course term day knowledge
        long todayKnowledge = this.loadCourseTermDayKnowledge(userId, courseId, termId,
                date.getDay());
        todayKnowledge += newKnowledge;
        this.saveCourseTermDayKnowledge(userId, courseId, termId, todayKnowledge, date.getDay());
        // update user knowledge
        long userKnowledge = this.loadKnowledge(userId);
        userKnowledge += newKnowledge;
        this.saveKnowledge(userId, userKnowledge);

        long newExp = newKnowledge * BASE_KNOWLEDGE_EXP;
        // complete day
        if (!this.courseTermDayIsComplete(userId, courseId, termId, date.getDay())) {
            if (todayKnowledge >= DAY_COMPLETE_KNOWLEDGE) {
                this.courseTermDayComplete(userId, courseId, termId, date.getDay());
                long completeDayCount = this.loadCourseTermCompleteDayCount(userId, courseId,
                        termId);
                ++completeDayCount;
                this.saveCourseTermCompleteDayCount(userId, courseId, termId, completeDayCount);
                // raise exp TODO judge
                // if (completeDayCount % SEVEN_DAY == 0) {
                // newExp += SEVEN_DAY_COMPLETE_EXP;
                // } else {
                newExp += DAY_COMPLETE_EXP;
                // }
            }
        }
        // update user Exp
        long userExp = this.loadExp(userId);
        userExp += newExp;
        this.saveExp(userId, userExp);
        // update course term exp
        long courseExp = this.loadCourseTermExp(userId, courseId, termId);
        courseExp += newExp;
        this.saveCourseTermExp(userId, courseId, termId, courseExp);
        // update user week exp
        long userWeekExp = this.loadWeekExp(userId, date.getWeek());
        userWeekExp += newExp;
        this.saveWeekExp(userId, date.getWeek(), userWeekExp);
        // update user course term week exp
        long courseWeekExp = this.loadCourseTermWeekExp(userId, courseId, termId, date.getWeek());
        courseWeekExp += newExp;
        this.saveCourseTermWeekExp(userId, courseId, termId, date.getWeek(), courseWeekExp);
        return newExp;
    }

    public List<Boolean> loadCourseTermWeekCompleteStatus(long userId, long courseId, long termId) {
        List<Boolean> weekCompleteStatus = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < dayOfWeek; ++i) {
            cal.add(Calendar.DATE, -(dayOfWeek - i - 1));
            String day = dateFormat.format(cal.getTime());
            boolean isComplete = this.courseTermDayIsComplete(userId, courseId, termId, day);
            weekCompleteStatus.add(isComplete);
            cal.add(Calendar.DATE, dayOfWeek - i - 1);
        }
        return weekCompleteStatus;
    }

    @Override
    public UserCourseTermStatus loadCourseTermStatus(long userId, long courseId, long termId) {
        UserCourseTermStatus status = new UserCourseTermStatus();
        Date date = TimeUtils.getDate();
        long exp = this.loadCourseTermExp(userId, courseId, termId);
        status.setExp(exp);
        long totalKnowledge = this.loadCourseTermKnowledge(userId, courseId, termId);
        status.setTotalKnowledge(totalKnowledge);
        long dayKnowledge = this.loadCourseTermDayKnowledge(userId, courseId, termId,
                date.getDay());
        status.setDayKnowledge(dayKnowledge);
        long completeDayCount = this.loadCourseTermCompleteDayCount(userId, courseId, termId);
        status.setSustainedCompleteDayCount(completeDayCount);
        // week complete
        List<Boolean> weekCompleteStatus = this.loadCourseTermWeekCompleteStatus(userId, courseId,
                termId);
        status.setWeekCompleteStatus(weekCompleteStatus);
        // get rank
        int rank = this.loadCourseTermRankValue(userId, courseId, termId);
        status.setRank(rank);
        // get level
        long levelId = this.loadCourseTermLevel(userId, courseId, termId);
        LevelInfo level = levelData.get(levelId);
        status.setLevel(level);
        return status;
    }

    @Override
    public UserCollegeStatus loadStatus(long userId) {
        UserCollegeStatus status = new UserCollegeStatus();
        long exp = this.loadExp(userId);
        status.setExp(exp);
        long knowledge = this.loadKnowledge(userId);
        status.setKnowledge(knowledge);
        int rank = this.loadRankValue(userId);
        status.setRank(rank);
        return status;
    }

    @Override
    public UserLessonStatus loadLessonStatus(long userId, long courseId, long termId,
            long lessonId) {
        UserLessonStatus status = new UserLessonStatus();
        boolean isComplete = this.courseTermLessonIsComplete(userId, courseId, termId, lessonId);
        status.setIsComplete(isComplete);
        if (!isComplete) {
            return status;
        }
        long knowledge = this.loadCourseTermLessonKnowledge(userId, courseId, termId, lessonId);
        long exp = knowledge * BASE_KNOWLEDGE_EXP;
        status.setExp(exp);
        int questionCount = lessonData.getQuestionCount(lessonId);
        double correctPercent = knowledge * 1.0 / questionCount;
        status.setCorrectPercent(correctPercent);
        return status;
    }
    
    private int buildUserRankValue(long userId, List<RankInfo> rankInfoList) {
        for(RankInfo rankInfo:rankInfoList) {
            if(rankInfo.getUserId() == userId) {
                return rankInfo.getRank();
            }
        }
        return 0;
    }

    @Override
    public int loadRankValue(long userId) {
        long friendCount = cUserFriendService.getFriendCount(userId);
        List<Long> friendIdList = cUserFriendService.getFriendIds(userId, 0, friendCount - 1);
        friendIdList.add(userId);
        List<Long> friendExpList = this.loadExp(friendIdList);
        List<RankInfo> rankInfoList = buildRankList(friendIdList, friendExpList);
        return buildUserRankValue(userId, rankInfoList);
    }

    @Override
    public int loadCourseTermRankValue(long userId, long courseId, long termId) {
        // TODO
        List<Long> courseTermUserIdList = new ArrayList<>();
        List<Long> expList = this.loadCourseTermExp(courseTermUserIdList, courseId, termId);
        List<RankInfo> rankInfoList = buildRankList(courseTermUserIdList, expList);
        return buildUserRankValue(userId, rankInfoList);
    }

    private List<UserRank> buildUserRankList(List<RankInfo> rankInfoList) {
        if (null == rankInfoList || rankInfoList.isEmpty()) {
            return Collections.emptyList();
        }
        List<UserRank> result = new ArrayList<>();
        List<Long> userIdList = new ArrayList<>();
        for (RankInfo rankInfo : rankInfoList) {
            userIdList.add(rankInfo.getUserId());
        }
        List<UserInfo> userInfoList = userService.loadUserInfo(userIdList);
        for (int i = 0; i < rankInfoList.size(); ++i) {
            UserRank userRank = new UserRank();
            userRank.setUserInfo(userInfoList.get(i));
            userRank.setRankInfo(rankInfoList.get(i));
            result.add(userRank);
        }
        return result;
    }
    
    private List<RankInfo> buildRankList(List<Long> userIdList, List<Long> userExpList) {
        List<RankInfo> userRankList = new ArrayList<>();
        for (int i=0;i <userIdList.size(); ++i){
            RankInfo rankInfo = new RankInfo();
            rankInfo.setUserId(userIdList.get(i));
            rankInfo.setExp(userExpList.get(i));
            userRankList.add(rankInfo);
        }
        RankEntityCompartor compartor = new RankEntityCompartor();
        Collections.sort(userRankList, compartor);
        int realRank = 0;
        int rank = realRank;
        long lastExp = Long.MAX_VALUE;
        for (RankInfo rankInfo : userRankList) {
            ++realRank;
            if (rankInfo.getExp() != lastExp) {
                rank = realRank;
            }
            rankInfo.setRank(rank);
            lastExp = rankInfo.getExp();
        }
        return userRankList;
    }

    @Override
    public List<UserRank> loadRankList(long userId) {
        long friendCount = cUserFriendService.getFriendCount(userId);
        List<Long> friendIdList = cUserFriendService.getFriendIds(userId, 0, friendCount - 1);
        friendIdList.add(userId);
        List<Long> friendExpList = this.loadExp(friendIdList);
        List<RankInfo> rankInfoList = buildRankList(friendIdList, friendExpList);
        return buildUserRankList(rankInfoList);
    }

    @Override
    public List<UserRank> loadCourseTermRankList(long userId, long courseId, long termId) {
        // TODO
        List<Long> courseTermUserIdList = new ArrayList<>();
        List<Long> expList = this.loadCourseTermExp(courseTermUserIdList, courseId, termId);
        List<RankInfo> rankInfoList = buildRankList(courseTermUserIdList, expList);
        return buildUserRankList(rankInfoList);
    }

    @Override
    public List<UserRank> loadWeekRankList(long userId) {
        Date date = TimeUtils.getDate();
        long friendCount = cUserFriendService.getFriendCount(userId);
        List<Long> friendIdList = cUserFriendService.getFriendIds(userId, 0, friendCount - 1);
        friendIdList.add(userId);
        List<Long> friendExpList = this.loadWeekExp(friendIdList, date.getWeek());
        List<RankInfo> rankInfoList = buildRankList(friendIdList, friendExpList);
        return buildUserRankList(rankInfoList);
    }

    @Override
    public List<UserRank> loadCourseTermWeekRankList(long userId, long courseId, long termId) {
        Date date = TimeUtils.getDate();
        // TODO
        List<Long> courseTermUserIdList = new ArrayList<>();
        List<Long> expList = this.loadCourseTermWeekExp(courseTermUserIdList, courseId, termId, date.getWeek());
        List<RankInfo> rankInfoList = buildRankList(courseTermUserIdList, expList);
        return buildUserRankList(rankInfoList);
    }
    /**
     * @author dusc
     */
     public void userPurchaseTerm(long userId,long courseId,long termId){
    	 userCourseTermMapper.userPurchaseTerm(userId,courseId, termId);
     }

	@Override
	public List<CourseInfo> getAllCourseInfo() {
		// TODO Auto-generated method stub
		return courseMapper.getAllCourseInfo();
	}
	
	 /**
     * 获取用户相关的课程id
     */
	@Override
	public List<CourseTermInfo> getCourseTermInfoByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userCourseTermMapper.getCourseTermInfoByUserId(userId);
	}
}
