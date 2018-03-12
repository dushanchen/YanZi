package com.yanzi.pisces.controller;

import java.util.ArrayList;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.common.constants.ReturnCode;
import com.yanzi.common.constants.SuccessCode;
import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.Date;
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.term.TermCourse;
import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermLesson;
import com.yanzi.common.entity.term.TermPrimer;
import com.yanzi.common.entity.user.BillsInfo;
import com.yanzi.common.redis.user.CUserCollegeRedisDao;
import com.yanzi.common.service.CUserCollegeService;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.common.utils.TimeUtils;
import com.yanzi.pisces.controller.param.SubmitQuestionParams;
import com.yanzi.pisces.controller.param.UserLoadCourseRankParams;
import com.yanzi.pisces.controller.param.UserLoadCourseStatusParams;
import com.yanzi.pisces.controller.param.UserLoadCoursesParams;
import com.yanzi.pisces.controller.param.UserLoadLessonInfoParams;
import com.yanzi.pisces.controller.param.UserLoadLessonsParams;
import com.yanzi.pisces.controller.param.UserLoadRankParams;
import com.yanzi.pisces.controller.param.UserLoadTermInfoParams;
import com.yanzi.pisces.controller.param.UserLoadTermsParams;
import com.yanzi.pisces.controller.response.ViewCheckPurchaseResponse;
import com.yanzi.pisces.controller.response.ViewSubmitQuestionResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadCourseStatusResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadCoursesResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadLessonInfoResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadLessonsResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadRankResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadTermInfoResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadTermsResponse;
import com.yanzi.pisces.controller.response.ViewUserPurchaseResponse;
import com.yanzi.pisces.data.CourseData;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.TermData;
import com.yanzi.pisces.entity.TermStatus;
import com.yanzi.pisces.entity.UserCourseInfo;
import com.yanzi.pisces.entity.UserLessonInfo;
import com.yanzi.pisces.entity.UserLessonStatus;
import com.yanzi.pisces.entity.UserRank;
import com.yanzi.pisces.entity.UserCollegeStatus;
import com.yanzi.pisces.entity.UserCourseTermStatus;
import com.yanzi.pisces.entity.UserTermInfo;
import com.yanzi.pisces.entity.UserTermStatus;
import com.yanzi.pisces.service.UserCollegeService;
import com.yanzi.pisces.service.UserService;


@Controller
public class UserController extends BaseController<ViewResponseBase> {

    @Autowired
    private TermData termData;
    @Autowired
    private CourseData courseData;
    @Autowired
    private LessonData lessonData;
 
    

    @Autowired
    private UserService userService;
    

    
    @Autowired
    private ParamsUtils paramsUtils;
    @Autowired
    private UserCollegeService userCollegeService;
    @Autowired
    private CUserCollegeRedisDao cUserCollegeRedisDao;
    @Autowired
    private CUserCollegeService cUserCollegeService;
/**
 * 获取所有的学期信息，及用户的学期信息
 * @param params
 * @return
 */
    @ResponseBody
    @RequestMapping(value = "/user/load/terms", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadTerm(@Valid UserLoadTermsParams params) {
        ViewUserLoadTermsResponse response = new ViewUserLoadTermsResponse();
        long userId = paramsUtils.getUserId(params);
        List<TermInfo> saleValidTerms = termData.getSaleValidList();//获取学期
        List<UserTermInfo> userTermInfos = new ArrayList<>();
        for (TermInfo termInfo : saleValidTerms) {
            long termId = termInfo.getId();
            long courseId=userCollegeService.getCourseIdByTermId(termId);
            termInfo.setCourseId(courseId);
            TermPrimer termPrimer = termData.getTermPrimer(termId);
            // TODO
            UserTermStatus userStatus = new UserTermStatus();//用户购买状态
            List<Long> userIds =new ArrayList<>();
            userIds = userService.selectUserIdByTermId(termId);
            boolean check = false;
            if (userIds!=null) {
            	 for (Long userIdItem : userIds) {
     				if (userIdItem==userId) {
     					check = true;
     				}
     			}
			} 
            userStatus.setPurchase(check);
            // TODO
            TermStatus termStatus = new TermStatus();//购买人数
            if (userIds!=null) {
            	termStatus.setPurchaseCount(userIds.size());
			}
            UserTermInfo userTermInfo = new UserTermInfo(termInfo, termPrimer, userStatus,
                    termStatus);
            userTermInfos.add(userTermInfo);
        }
        response.setTermInfos(userTermInfos);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/terminfo", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadTermInfo(
            @Valid UserLoadTermInfoParams params) {
        ViewUserLoadTermInfoResponse response = new ViewUserLoadTermInfoResponse();
        long termId = params.getTermId();
        long userId = paramsUtils.getUserId(params);
        TermInfo termInfo = termData.get(termId);
        TermPrimer termPrimer = termData.getTermPrimer(termId);
        // TODO
        TermStatus termStatus = new TermStatus();
        // TODO
        UserTermStatus userStatus = new UserTermStatus();
        UserTermInfo userTermInfo = new UserTermInfo(termInfo, termPrimer, userStatus, termStatus);
        response.setTermInfo(userTermInfo);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/courses", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadCourse(
            @Valid UserLoadCoursesParams params) {
        ViewUserLoadCoursesResponse response = new ViewUserLoadCoursesResponse();
        long userId = paramsUtils.getUserId(params);
        List<Long> userCourseIds = userCollegeService.loadCourseIdList(userId);
        List<UserCourseInfo> userCourses = new ArrayList<>();
        for (long userCourseId : userCourseIds) {
            CourseInfo courseInfo = courseData.get(userCourseId);
            if(courseInfo.getId()!=0){//valid参数为1时获取的对象为空 但valid参数默认为0 通过Id来筛选
            	UserCourseInfo userCourseInfo = new UserCourseInfo(courseInfo);
            	userCourses.add(userCourseInfo);
            }
            else
            	continue;
        }
        response.setCourseInfos(userCourses);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/lessoninfo", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadLesson(
            @Valid UserLoadLessonInfoParams params) {
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long termId = userCollegeService.loadCourseTermId(userId, courseId);
        long lessonId = params.getLessonId();
        ViewUserLoadLessonInfoResponse response = new ViewUserLoadLessonInfoResponse();
        LessonInfo lessonInfo = lessonData.get(lessonId);
        LessonPrimer lessonPrimer = lessonData.getLessonBrief(lessonId);
        LessonSummary lessonSummary = lessonData.getLessonSummary(lessonId);
        UserLessonStatus userLessonStatus = userCollegeService.loadLessonStatus(userId, courseId,
                termId, lessonId);
        int questionCount = lessonData.getQuestionCount(lessonId);
        
        UserLessonInfo userLessonInfo = new UserLessonInfo();
//        userLessonInfo = new UserLessonInfo(lessonInfo,lessonPrimer,
//                lessonSummary,userLessonStatus,questionCount);
        userLessonInfo.setLessonInfo(lessonInfo);
        userLessonInfo.setLessonPrimer(lessonPrimer);
        userLessonInfo.setLessonSummary(lessonSummary);
        userLessonInfo.setQuestionCount(questionCount);
        userLessonInfo.setUserLessonStatus(userLessonStatus);
        
        response.setLessonInfo(userLessonInfo);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/lessons", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadCourseLesson(
            @Valid UserLoadLessonsParams params) {
        ViewUserLoadLessonsResponse response = new ViewUserLoadLessonsResponse();
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long termId = userCollegeService.loadCourseTermId(userId, courseId); //用户买了该课程哪期
        List<TermLesson> termLessonList = termData.getTermLessonList(termId); //该期下有哪些lessonId
        List<Long> lessonIds = courseData.getLessonIdList(courseId);  //该课程下有哪些lessonId
        
        List<LessonInfo> lessonInfos = lessonData.get(lessonIds);     //lessonId List获取lessonInfo List
        List<UserLessonInfo> userLessonInfos = new ArrayList<>();
        
        for (TermLesson termLesson : termLessonList) {   	//期下所有关卡遍历
        	for (LessonInfo lessonInfo : lessonInfos) {     //lessonInfo中的Id和期内关卡Id对应上了
                if (termLesson.getLessonId() == lessonInfo.getId()) {
                	long lessonId=lessonInfo.getId();
                    UserLessonStatus userStatus = userCollegeService.loadLessonStatus(userId,
                            courseId, termId, lessonId); //获取该课程的课程状态
                    
                    //2018.3.10 primer summary termLessonInfo字段补充 hx
                    LessonPrimer lessonPrimer=lessonData.getLessonBrief(lessonId);
                    LessonSummary lessonSummary=lessonData.getLessonSummary(lessonId);
                    int questionCount=lessonData.getQuestionCount(lessonId);
                    TermLesson termLessonInfo=lessonData.getTermLesson(lessonId);
                    
                    UserLessonInfo userLessonInfo = new UserLessonInfo(); //容器不可以放循环体外面
                    userLessonInfo.setLessonInfo(lessonInfo);
                    userLessonInfo.setTermLesson(termLessonInfo);
                    userLessonInfo.setLessonPrimer(lessonPrimer);
                    userLessonInfo.setLessonSummary(lessonSummary);
                    userLessonInfo.setQuestionCount(questionCount);
                    userLessonInfo.setUserLessonStatus(userStatus);
                    
                    userLessonInfos.add(userLessonInfo);
                }
            }	
        }
        response.setLessonInfos(userLessonInfos);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/course/status", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadCourseStatus(
            @Valid UserLoadCourseStatusParams params) {
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long termId = userCollegeService.loadCourseTermId(userId, courseId);

        ViewUserLoadCourseStatusResponse response = new ViewUserLoadCourseStatusResponse();
        UserCourseTermStatus userCourseTermStatus = userCollegeService.loadCourseTermStatus(userId,
                courseId, termId);
        response.setUserCourseTermStatus(userCourseTermStatus);
        UserCollegeStatus userCollegeStatus = userCollegeService.loadStatus(userId);
        response.setUserCollegeStatus(userCollegeStatus);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/rank", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadRank(@Valid UserLoadRankParams params) {
        long userId = paramsUtils.getUserId(params);
        ViewUserLoadRankResponse response = new ViewUserLoadRankResponse();
        List<UserRank> userRanks = userCollegeService.loadRankList(userId);
        response.setUserRanks(userRanks);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/week/rank", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadWeekRank(
            @Valid UserLoadRankParams params) {
        long userId = paramsUtils.getUserId(params);
        ViewUserLoadRankResponse response = new ViewUserLoadRankResponse();
        List<UserRank> userRanks = userCollegeService.loadWeekRankList(userId);
        response.setUserRanks(userRanks);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/course/rank", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadCourseRank(
            @Valid UserLoadCourseRankParams params) {
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long termId = userCollegeService.loadCourseTermId(userId, courseId);//
        ViewUserLoadRankResponse response = new ViewUserLoadRankResponse();
        List<UserRank> userRanks = userCollegeService.loadCourseTermRankList(userId, courseId,
                termId);
        response.setUserRanks(userRanks);
        return packageSuccessData(response);
    }

    @ResponseBody
    @RequestMapping(value = "/user/load/course/week/rank", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> userLoadCourseWeekRank(
            @Valid UserLoadCourseRankParams params) {
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long termId = userCollegeService.loadCourseTermId(userId, courseId);
        ViewUserLoadRankResponse response = new ViewUserLoadRankResponse();
        List<UserRank> userRanks = userCollegeService.loadCourseTermWeekRankList(userId, courseId,
                termId);
        response.setUserRanks(userRanks);
        return packageSuccessData(response);
    }
 /**
  * 用户购买学期
  * @param params(token,termId,courseId)
  * @return
  * @author dusc
  */
    @ResponseBody
    @RequestMapping(value="/user/purchase",method = { RequestMethod.GET,RequestMethod.POST })
    
    public ResponseEntity<ResponseEntityWrapper> purchaseTerm(@Valid UserLoadTermInfoParams params){
    	long userId = paramsUtils.getUserId(params);
    	long termId = params.getTermId();
    	long courseId = params.getCourseId();
    	double coins = params.getPrice();
    	 // TODO  支付过程
    	
    	//先查询该课程是否已经购买
    	List<BillsInfo> billsinfo=userCollegeService.checkPurchase(userId,courseId, termId);//checkPurchase函数也可以查CourseTerm里的数据
    	if(billsinfo.isEmpty()){
   		 	
   		 	
   		 userCollegeService.userPurchaseTerm(userId,courseId, termId,coins);//扣钱 加入人课索引关系（数据库插更 redis覆盖） 
	        
	        userCollegeService.userPurchase(userId, courseId, termId, coins);//生成流水
	        ViewUserPurchaseResponse response=new ViewUserPurchaseResponse();
	        BillsInfo billsInfo=new BillsInfo();
	        billsInfo.setUserId(userId);
	        billsInfo.setCourseId(courseId);
	        billsInfo.setNumber(coins);
	        billsInfo.setState(false);
	        billsInfo.setTermId(termId);
	        response.setBillsInfo(billsInfo);
	        return packageSuccessData(response);
   		 	
    	}
    	else{
    		ViewCheckPurchaseResponse response=new ViewCheckPurchaseResponse();
   		 	int type=100002;
   		 	String des="用户已购买该期课程";
   		 	response.setType(type);
   		 	response.setDes(des);
   		 	//return packageSuccessData(response);
   		 	return packageSuccessData(response,2,type);//2是SuccesssCode的code参数
    	}
        
    }
    
    
    

	/**
     * 提交问题
     * @author 朱江游
     * @param params
     * long userId, long courseId, long lessonId, long lessonKnowledge
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/submit/question", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> submitQuestion(
            @Valid SubmitQuestionParams params) {
        long userId = paramsUtils.getUserId(params);
        long courseId = params.getCourseId();
        long lessonId = params.getLessonId();
        int exp = params.getExp();
        int lessonKnowledge = params.getKnowledge();
        
        //更新课程经验值
        long newExp = userCollegeService.completeLesson(userId, courseId, lessonId, lessonKnowledge,exp);
        ViewSubmitQuestionResponse response = new ViewSubmitQuestionResponse();
        
        response.setNewExp(newExp);
        userCollegeService.saveLatestLesson(userId,lessonId);//保存用户最近完成的关卡 dusc
        boolean courseTermDayIsComplete = false;
        long termId = userService.selectUserTermIdByUserIdAndCourseId(userId, courseId);
        Date date = TimeUtils.getDate();
        courseTermDayIsComplete = cUserCollegeService.courseTermDayIsComplete(userId, courseId, termId, date.getDay());
        List<Boolean>  loadCourseTermWeekCompleteStatus = userCollegeService.loadCourseTermWeekCompleteStatus(userId, courseId, termId);
        boolean flag = true;
    	for(int i=0;i<loadCourseTermWeekCompleteStatus.size();i++){
    		if(!loadCourseTermWeekCompleteStatus.get(i)){
    			flag = false;
    			break;
    		}
    	}
        response.setCourseTermDayIsComplete(courseTermDayIsComplete);
        response.setLoadCourseTermWeekCompleteStatus(flag);
        		
        		
        return packageSuccessData(response);
      
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
