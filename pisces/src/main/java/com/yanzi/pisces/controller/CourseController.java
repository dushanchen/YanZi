package com.yanzi.pisces.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.entity.college.level.LevelInfo;
import com.yanzi.common.service.CUserService;
import com.yanzi.common.service.CourseService;
import com.yanzi.common.utils.ParamsUtils;
import com.yanzi.pisces.controller.param.BaseParam;
import com.yanzi.pisces.controller.response.ViewCourseResponse;
import com.yanzi.pisces.controller.response.ViewUserCourseLevelResponse;
import com.yanzi.pisces.service.UserCollegeService;

@Controller
public class CourseController extends BaseController<ViewResponseBase> {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CUserService cUserService;
    @Autowired
    private UserCollegeService userCollegeService;
    @Autowired
    private ParamsUtils paramsUtils;

    @ResponseBody
    @RequestMapping(value = "/load/allcourse", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> loadAllCourse(@Valid BaseParam params) {
//        List<CourseInfo> courseList = courseService.getAllCourseList();
        List<CourseInfo> courseList = userCollegeService.getAllCourseInfo();
        return packageSuccessData(new ViewCourseResponse(courseList));
    }
    
    @ResponseBody
    @RequestMapping(value = "/user/load/alllevel", method = { RequestMethod.GET,
            RequestMethod.POST })
    public ResponseEntity<ResponseEntityWrapper> loadUserAllLevels(
            @Valid UserActionParamsBase params) {
    	long userId = paramsUtils.getUserId(params);
        ViewUserCourseLevelResponse response = new ViewUserCourseLevelResponse();
//        List<Long> curriculumIds = cUserService.loadUserSubscribedCourseId(userId);
        List<Long> courseIds = userCollegeService.getCourseIdByUserId(userId);
        List<LevelInfo> levels = new ArrayList<>();
        for (long courseId : courseIds) {
            long exp = cUserService.loadUserCourseExp(userId, courseId);
            LevelInfo level = courseService.getLevelByExp(courseId, exp);
            levels.add(level);
        }
        response.setLevels(levels);
        return packageSuccessData(response);
    }
}