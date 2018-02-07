package com.yanzi.pisces.controller;

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
import com.yanzi.common.entity.college.question.QuestionInfo;
import com.yanzi.pisces.controller.param.LoadQuestionParams;
import com.yanzi.pisces.controller.response.ViewLoadQuestionResponse;
import com.yanzi.pisces.service.QuestionService;

@Controller
public class QuestionController extends BaseController<ViewResponseBase> {
    
    @Autowired
    private QuestionService questionService;
//courseId?
    @ResponseBody
    @RequestMapping(value = "/load/question", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> loadQuestion(@Valid LoadQuestionParams params) {
        QuestionInfo questionInfo = questionService.loadQuestion(params.getLessonId(), params.getIndex());
        ViewLoadQuestionResponse response = new ViewLoadQuestionResponse();
        response.setQuestion(questionInfo);
        return packageSuccessData(response);
    }
    
    
  
    
    
    
}
