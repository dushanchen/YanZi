package com.yanzi.taurus.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.yanzi.common.controller.BaseController;
import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.common.service.CUserCollegeService;
import com.yanzi.taurus.service.UserService;
import com.yanzi.taurus.view.ViewShareCurriculumKnowledgeResponse; 

public class ShareController extends BaseController<ViewResponseBase> {

	@Autowired
    private UserService userService;
	
	@Autowired
	private CUserCollegeService collegeService;
	
	
	@RequestMapping(value = "/share/curriculum/knowledge", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
	public void shareKnowledge(@Valid UserActionParamsBase params){
		long userId = userService.loadUserId(params.getToken());
		ViewShareCurriculumKnowledgeResponse response = new ViewShareCurriculumKnowledgeResponse();
		response.setUserInfo(userService.loadUserInfo(userId));
	         
//        long susTainedCompleteDayCount = collegeService
//                .getCurriculumSustainedCompleteDayCount(userId);
//        response.setSustainedCompleteDayCount(susTainedCompleteDayCount);
        long knowledge = collegeService.loadKnowledge(userId);
        response.setKnowledge(knowledge);

        long lessonId = geminiUserService.getCurriculumLatestCompletedLesson(userId);
        response.setLesson(curriculumData.getlessonInfoById(lessonId));
        response.setLessonBackgroud(curriculumData.getLessonBackgroudById(lessonId));

        return packageSuccessData(response);
	}
}
