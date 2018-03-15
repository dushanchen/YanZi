package com.yanzi.pisces.job.mysql;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.common.entity.college.course.CourseInfo;
import com.yanzi.common.job.MysqlLoadJob;
import com.yanzi.common.trace.MLogger;
import com.yanzi.common.utils.EnvUtils;
import com.yanzi.pisces.data.CourseData;
import com.yanzi.pisces.mysql.CourseMapper;
/**
 * 定时更新课程内容，根据最后更新时间
 * @author dsczijizuo
 *
 */
public class CourseIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(CourseIncrLoadJob.class);

    @Autowired
    private CourseData courseData;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, CourseInfo> courseMap;

    @Override
    protected void beforeRun() {
        courseMap = courseData.getCourseInfoMap();
    }

    @Override
    protected void mysqlLoad() {
        List<CourseInfo> courseList = courseMapper.selectByRangTime(beginTime, endTime);
        if (null == courseList || courseList.isEmpty()) {
            return;
        }
        for (CourseInfo course : courseList) {
            if (course.getValid() > envUtils.getEnvValid().getValue()) {
                courseMap.remove(course.getId());
            } else {
                courseMap.put(course.getId(), course);
            }
         }
        System.out.println(courseMap.size());
     }
 }
