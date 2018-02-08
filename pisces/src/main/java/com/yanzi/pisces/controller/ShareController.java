package com.yanzi.pisces.controller;

import javax.validation.Valid;

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
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.pisces.service.UserCollegeService;
import com.yanzi.pisces.service.UserService;
import com.yanzi.pisces.controller.param.UserLoadTermInfoParams;
import com.yanzi.pisces.controller.response.ViewShareCurriculumExpResponse;
import com.yanzi.pisces.controller.response.ViewShareCurriculumKnowledgeResponse;
import com.yanzi.pisces.controller.response.ViewShareCurriculumLessonResponse;
import com.yanzi.pisces.data.LessonData;
import com.yanzi.pisces.data.LevelData;
@Controller
public class ShareController extends BaseController<ViewResponseBase> {

	@Autowired
    private UserService userService;
	
	@Autowired
	private UserCollegeService collegeService;
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
	public ResponseEntity<ResponseEntityWrapper> shareKnowledge(@Valid UserActionParamsBase params){
		long userId = paramsUtils.getUserId(params);
		ViewShareCurriculumKnowledgeResponse response = new ViewShareCurriculumKnowledgeResponse();
		response.setUserInfo(userService.loadUserInfo(userId));
	         
//        long susTainedCompleteDayCount = collegeService
//                .getCurriculumSustainedCompleteDayCount(userId);
//        response.setSustainedCompleteDayCount(susTainedCompleteDayCount);
        long knowledge = collegeService.loadKnowledge(userId);
        response.setKnowledge(knowledge);

        long lessonId = collegeService.loadLatestLesson(userId);
        response.setLesson(lessonData.getLessonBrief(lessonId));
//        response.setLessonBackgroud(curriculumData.getLessonBackgroudById(lessonId));

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
		 
		ViewShareCurriculumExpResponse response = new ViewShareCurriculumExpResponse();
		response.setUserInfo(userService.loadUserInfo(userId));
		long exp = collegeService.loadExp(userId);
	    response.setExp(exp);
	    response.setLevelInfo(levelData.getByCourseIdAndExp(params.getCourseId(), exp));
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
