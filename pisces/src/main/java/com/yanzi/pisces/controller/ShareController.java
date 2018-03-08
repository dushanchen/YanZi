package com.yanzi.pisces.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.response.ResponseEntityWrapper;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.service.CUserCollegeService;
import com.yanzi.common.utils.ArrayToListUtil;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.pisces.service.UserCollegeService;
import com.yanzi.pisces.service.UserService;
import com.yanzi.pisces.controller.param.UserLoadPrimerParams;
import com.yanzi.pisces.controller.param.UserLoadTermInfoParams;
import com.yanzi.pisces.controller.response.ViewShareCurriculumExpResponse;
import com.yanzi.pisces.controller.response.ViewShareCurriculumKnowledgeResponse;
import com.yanzi.pisces.controller.response.ViewShareCurriculumLessonResponse;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.LevelData;
import com.yanzi.pisces.entity.UserRank;
import com.yanzi.pisces.entity.UserTermInfo;
@Controller
public class ShareController extends BaseController<ViewResponseBase> {

	@Autowired
    private UserService userService;
	@Autowired
	private UserCollegeService userCollegeService;
	@Autowired
	private UserCollegeService collegeService;
	@Autowired
	private CUserCollegeService cUserCollegeService;	
	@Autowired
    private LessonData lessonData;
	@Autowired
	private LevelData levelData;
	@Autowired
    private ParamsUtils paramsUtils;
	/**
	 * 任务分享
	 * @param params
	 * @return
	 * @author dusc
	 */
	@RequestMapping(value = "/share/curriculum/knowledge", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
	public ResponseEntity<ResponseEntityWrapper> shareKnowledge(@Valid UserLoadPrimerParams params){
		long userId = paramsUtils.getUserId(params);
		long courseId=params.getCourseId();
		ViewShareCurriculumKnowledgeResponse response = new ViewShareCurriculumKnowledgeResponse();
		response.setUserInfo(userService.loadUserInfo(userId));//加载个人信息
	         
//        long susTainedCompleteDayCount = collegeService
//                .getCurriculumSustainedCompleteDayCount(userId);
//        response.setSustainedCompleteDayCount(susTainedCompleteDayCount);
        long knowledge = collegeService.loadKnowledge(userId);//知识点获取
        response.setKnowledge(knowledge);
        
        //long lessonId = collegeService.loadLatestLesson(userId,courseId);//关卡获取 token+courseId 拉取lessonId最后一行数据
        long termId=userService.selectUserTermIdByUserIdAndCourseId(userId,courseId);
        long lessonId=userService.loadLatestLesson(termId);
        response.setLesson(lessonData.getLessonBrief(lessonId));
//        response.setLessonBackgroud(curriculumData.getLessonBackgroudById(lessonId));
        
        long insistTime=cUserCollegeService.loadCourseTermCompleteDayCount(userId,courseId,termId);//课程坚持天数获取
        response.setDurationTime(insistTime);
        
        
        return packageSuccessData(response);
	}
	/**
	 * 勋章分享
	 * @param params
	 * @return
	 * @author dusc
	 */
	@RequestMapping(value = "/share/curriculum/exp", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
	public ResponseEntity<ResponseEntityWrapper> shareExp(@Valid UserLoadTermInfoParams params){
		long userId = paramsUtils.getUserId(params);
		long courseId = params.getCourseId();
		 
		ViewShareCurriculumExpResponse response = new ViewShareCurriculumExpResponse();
		response.setUserInfo(userService.loadUserInfo(userId));
		long exp = collegeService.loadExp(userId);
	    response.setExp(exp);
	    response.setLevelInfo(levelData.getByCourseIdAndExp(courseId, exp));
	    
	    //打败好友数获取
	    long termId = userCollegeService.loadCourseTermId(userId, courseId);
	    List<UserRank> userRanks = userCollegeService.loadCourseTermRankList(userId, courseId,
                termId);
	    //获取的List进行好友筛选???
	    List<UserRank> fuserRanks=new ArrayList<>();
	    for(UserRank userRank:userRanks){
	    	long tempId=userRank.getUserInfo().getId();//获取每个对象的userId
	    	UserRank fuserRank= new UserRank();     
	    	if(userCollegeService.checkFriend(userId,tempId)){ //判断是否好友
		    	fuserRank.setUserInfo(userRank.getUserInfo());//新List容器接收好友RankList
		    	fuserRank.setRankInfo(userRank.getRankInfo());
		    	fuserRanks.add(fuserRank);
	    	}
	    }
	    
	    int all=fuserRanks.size();
	    int rank=userCollegeService.loadCourseTermRank(userId, courseId,
                termId,fuserRanks);
	    response.setBeatCount(all-rank);//好友总数-rank排名
	    return packageSuccessData(response);
	}
	
	/**
	 * 关卡分享
	 * @param params
	 * @return
	 * @author dusc
	 */
	@RequestMapping(value = "/share/course/lesson", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
	public ResponseEntity<ResponseEntityWrapper> shareLesson(@Valid UserLoadTermInfoParams params){
		long userId = paramsUtils.getUserId(params);
		long courseId = params.getCourseId();
		long termId = params.getTermId();
		long lessonId = params.getLessonId();
		
		ViewShareCurriculumLessonResponse response = new ViewShareCurriculumLessonResponse();
		response.setUserInfo(userService.loadUserInfo(userId));//用户信息
		response.setLessonInfo(lessonData.get(lessonId));//关卡信息
		//关卡知识点
		response.setKnowledge(collegeService.loadCourseTermLessonKnowledge(userId, courseId, termId, lessonId));
	    //关卡最大知识点
		response.setMaxKnowledge(collegeService.loadCourseTermLessonMaxKnowledge(userId, courseId, termId, lessonId));
	    return packageSuccessData(response);
	}
	
}
