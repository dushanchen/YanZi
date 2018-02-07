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
import com.yanzi.pisces.service.UserCollegeService;
import com.yanzi.pisces.service.UserService; 
import com.yanzi.pisces.controller.response.ViewShareCurriculumKnowledgeResponse;
import com.yanzi.pisces.data.LessonData;
@Controller
public class ShareController extends BaseController<ViewResponseBase> {

	@Autowired
    private UserService userService;
	
	@Autowired
	private UserCollegeService collegeService;
	@Autowired
    private LessonData lessonData;
	
	@RequestMapping(value = "/share/curriculum/knowledge", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
	public ResponseEntity<ResponseEntityWrapper> shareKnowledge(@Valid UserActionParamsBase params){
		long userId = userService.loadUserId(params.getToken());
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
}
