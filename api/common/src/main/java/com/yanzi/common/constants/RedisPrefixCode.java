package com.yanzi.common.constants;

public enum RedisPrefixCode {
//    DEFAULT("0&&"), 
//    USER_ID("1&&"), 
//    COMMENT("2&&"), 
//    COMMENT_SOURCE("3&&"), 
//    GOD_COMMENT_SOURCE("4&&"), 
//    USER_INFO("5&&"),
//    LIKE("6&&"),
//    USER_LIKE("7&&"), 
//    USER_LESSON_SCORE("8&&"), 
//    USER_QUESTION_ANSWER("9&&"), 
//    USER_ALL_CURRICULUM_SCORE("10&&"), 
//    USER_QUESTION_ANSWER_TEMP("11&&"), 
//    USER_LESSON_CORRECT_QUESTION("12&&"), 
//    USER_LESSON_CORRECT_QUESTION_TEMP("13&&"),
//    USER_SUBSCRIBED_CURRICULUM_V2("14&&"),            // 用户订阅的课程
//    USER_LESSON_EXP_V2("15&&"),                       // 用户lesson exp
//    USER_CURRICULUM_EXP_V2("16&&"),                   // 用户 exp
//    USER_LESSON_MAX_CORRECT_COUNT_V2("17&&"),
//    USER_CURRICULUM_PLAN("18&&"),
//    USER_CURRICULUM_DAY_COMPLETE_AIM("19&&"),
//    USER_CURRICULUM_DAY_KNOWLEDGE_COUNT("20&&"),
//    USER_CURRICULUM_SUSTAIN_COMPLETE_AIM_DAY_COUNT("21&&"),
//    USER_CURRICULUM_KNOWLEDGE_COUNT("22&&"),
//    USER_DIALOG_RELATION("23&&"),
//    USER_APP_DURATION("24&&"),						//用户在线时长
//    USER_CURRICULUM_WEEK_EXP_V3("25&&"),
//    USER_CURRICULUM_LATEST_COMPLETED_LESSON("26&&"),
//    USER_ID_TO_IDOL("27&&"),
//    USER_ID_TO_FANS("28&&"),

    UNKNOW("A00000&&"),
    USER_TOKEN_TO_USER_ID("A00001&&"),
    USER_ID_TO_USER_INFO("A00002&&"),
    USER_ID_TO_APP_DURATION("A00003&&"),
    USER_ID_LIST("A00004&&"),
    USER_ID_TO_USER_PERMISSION("A00005&&"),
    USER_ID_TO_IDOL("A00006&&"),
    USER_ID_TO_FANS("A00007&&"),
    USER_ID_TO_PUSH_INFO("A00008&&"),

    USER_COLLEGE_COURSE_ID_TO_TERM_ID("B00001&&"),                   // 用户course对应的term
    USER_COLLEGE_EXP("B00002&&"),                                    // 用户EXP
    USER_COLLEGE_KNOWLEDGE("B00003&&"),                              // 用户knowledge
    USER_COLLEGE_LEVELS("B00004&&"),
    USER_COLLEGE_WEEK_EXP("B00005&&"),
    USER_COLLEGE_COURSE_TERM_EXP("B00101&&"),                        // 用户course exp
    USER_COLLEGE_COURSE_TERM_KNOWLEDGE("B00102&&"),                  // 用户course knowledge
    USER_COLLEGE_COURSE_TERM_DAY_KNOWLEDGE("B00103&&"),              // 用户course 天 knowledge
    USER_COLLEGE_COURSE_TERM_COMPLETE_DAY_COUNT("B00104&&"),         // 用户course 完成的天数 
    USER_COLLEGE_COURSE_TERM_DAY_COMPLETE("B00105&&"),               // 用户course 每天完成状态
    USER_COLLEGE_COURSE_TERM_WEEK_EXP("B00106&&"),
    USER_COLLEGE_COURSE_TERM_LEVEL("B00107&&"),
    
    USER_COLLEGE_COURSE_TERM_LESSON_MAX_KNOWLEDGE("B00201&&"),       // 用户course lesson最大正确数
    USER_COLLEGE_COURSE_TERM_LESSON_KNOWLEDGE("B00202&&"),           // 用户course lesson最大正确数

    USER_LATEST_COMPLETE_LESSON("B00203&&"),						 // 用户最近完成的课程					
    COLLEGE_TERM_USERS("C00001&&"),
    
    USER_ALL_COURSE_SCORE("D00101&&"),								//用户所有课程总分
    USER_SUBSCRIBED_COURSE_V2("D00102&&"),            				// 用户订阅的课程
    ;

    private String code;

    private RedisPrefixCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RedisPrefixCode getByName(String name) {
        try {
            return RedisPrefixCode.valueOf(name);
        } catch (Exception e) {
            return UNKNOW;
        }
    }
}
