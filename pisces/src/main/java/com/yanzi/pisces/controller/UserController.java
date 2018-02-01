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

import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.entity.college.lesson.LessonPrimer;
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.lesson.LessonInfo;
import com.yanzi.common.entity.college.lesson.LessonSummary;
import com.yanzi.common.entity.term.TermInfo;
import com.yanzi.common.entity.term.TermLesson;
import com.yanzi.common.entity.term.TermPrimer;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.pisces.controller.param.UserLoadCourseRankParams;
import com.yanzi.pisces.controller.param.UserLoadCourseStatusParams;
import com.yanzi.pisces.controller.param.UserLoadCoursesParams;
import com.yanzi.pisces.controller.param.UserLoadLessonInfoParams;
import com.yanzi.pisces.controller.param.UserLoadLessonsParams;
import com.yanzi.pisces.controller.param.UserLoadRankParams;
import com.yanzi.pisces.controller.param.UserLoadTermInfoParams;
import com.yanzi.pisces.controller.param.UserLoadTermsParams;
import com.yanzi.pisces.controller.response.ViewUserLoadCourseStatusResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadCoursesResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadLessonInfoResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadLessonsResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadRankResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadTermInfoResponse;
import com.yanzi.pisces.controller.response.ViewUserLoadTermsResponse;
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

@Controller
public class UserController extends BaseController<ViewResponseBase> {

    @Autowired
    private TermData termData;
    @Autowired
    private CourseData courseData;
    @Autowired
    private LessonData lessonData;

    @Autowired
    private ParamsUtils paramsUtils;
    @Autowired
    private UserCollegeService userCollegeService;
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
        List<TermInfo> saleValidTerms = termData.getSaleValidList();//获取还没开课的学期
        List<UserTermInfo> userTermInfos = new ArrayList<>();
        for (TermInfo termInfo : saleValidTerms) {
            long termId = termInfo.getId();
            TermPrimer termPrimer = termData.getTermPrimer(termId);
            // TODO
            UserTermStatus userStatus = new UserTermStatus();//用户购买状态
            // TODO
            TermStatus termStatus = new TermStatus();//购买人数
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
            UserCourseInfo userCourseInfo = new UserCourseInfo(courseInfo);
            userCourses.add(userCourseInfo);
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
        UserLessonInfo userLessonInfo = new UserLessonInfo(lessonInfo, lessonPrimer, lessonSummary,
                userLessonStatus, questionCount);
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
        long termId = userCollegeService.loadCourseTermId(userId, courseId);
        List<TermLesson> termLessonList = termData.getTermLessonList(termId);
        List<Long> lessonIds = courseData.getLessonIdList(courseId);
        List<LessonInfo> lessonInfos = lessonData.get(lessonIds);
        List<UserLessonInfo> userLessonInfos = new ArrayList<>();
        for (TermLesson termLesson : termLessonList) {
            for (LessonInfo lessonInfo : lessonInfos) {
                if (termLesson.getLessonId() == lessonInfo.getId()) {
                    UserLessonStatus userStatus = userCollegeService.loadLessonStatus(userId,
                            courseId, termId, termLesson.getLessonId());
                    UserLessonInfo userLessonInfo = new UserLessonInfo(lessonInfo, termLesson,
                            userStatus);
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
        long termId = userCollegeService.loadCourseTermId(userId, courseId);
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
  * @param params
  * @return
  */
    @ResponseBody
    @RequestMapping(value="/user/purchase",method = { RequestMethod.GET,RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> purchaseTerm(@Valid UserLoadTermInfoParams params){
    	long userId = paramsUtils.getUserId(params);
    	long termId = params.getTermId();
    	 // TODO  支付过程
        userCollegeService.userPurchaseTerm(userId, termId);
        return packageSuccessData(new ViewResponseBase());
        
    }
}
