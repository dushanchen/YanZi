package com.yanzi.common.constants;

public enum ReturnCode {
	UNKNOWN_ERROR(-99999, "未知错误"),
	
	USER_PERMISSIONS_FAILED(-1, "用户权限错误"),
	SUCCESS(0, "成功"), 

    PHONE_NO_IS_NOT_REGISTER(20001, "手机号未被注册过"),
    PHONE_NO_IS_REGISTER(20002, "手机号已被注册"),
    TOKEN_IS_NOT_EXIST(20003, "用户Token不存在"),
    USER_TAG_IS_NOT_EXIST(20004, "用户标签不存在"),
	
	PHONE_NO_IS_NULL(21001, "手机号不能为空"),
	PASSWORD_IS_NULL(21002, "密码不能为空"),
	TOKEN_IS_NULL(21003, "用户Token不能为空"),
	THIRD_PARTY_ID_IS_NULL(21004, "第三方ID不能为空"),
	VERIFI_CODE_IS_NULL(21005, "验证码不能为空"),
	
	PASSWORD_ERROR(22002, "密码错误"),
	VERIFI_CODE_IS_ERROR(22005, "验证码错误"),
	
	SMS_SEND_FAILED(23000, "信息发送失败"),
    SMS_DAY_MAX_NUM_EXCEED(23001, "超过短信最大发送次数"),
    SMS_VALID_TIME_EXCEED(23002, "验证信息已过期"),
    SMS_VERIFI_CODE_INVALID(23003, "验证码无效"),
    
    INVITE_CODE_IS_ERROR(24001, "邀请码错误"),
    
    THIRD_PARTY_ID_IS_BINDED(25001, "第三方ID已被绑定"),
    
    COMMENT_SOURCE_LEN_ERROR(40001, "评论来源错误，请检查source&sourceId"),
    COMMENT_IS_NOT_VALID(40002, "评论已经被删除"),
    
    LIKE_SOURCE_LEN_ERROR(50001, "点赞来源错误，请检查source&sourceId"),
    
    LESSON_IS_NOT_EXIST(60001, "关卡并不存在"),
    QUESIONT_INDEX_IS_EXCEEDED(61001, "问题越界"),
    QUESIONT_IS_NOT_EXIST(61002, "问题不存在"),
    NO_SUCH_QUESTION_IN_LESSON(61003, "关卡中不存在该问题"),
    ANSWER_IS_NOT_EXIST(62001, "问题不存在"),

    CHAT_USER_IS_NOT_OWNER(70001, "操作失败，用户不是创建者"),
    CHAT_USER_IS_NOT_MEMBER(70002, "操作失败，用户不是成员"),
    CHAT_USER_REGISTER_FAILED(70100, "用户注册聊天失败,请联系管理员"),
    CHAT_USER_IS_REGISTER(70101, "用户已经注册过聊天"),
    CHAT_USER_IS_NOT_REGISTER(70102, "用户尚为注册聊天"),
    CHAT_GROUP_CREATE_FAILED(70200, "聊天群创建失败"),
    CHAT_GROUP_REMOVE_FAILED(70201, "聊天群销毁失败"),
    CHAT_GROUP_IS_NOT_EXIST(70202, "聊天群不存在"),
    CHAT_GROUP_REMOVE_USER_FAILED(70202, "聊天群删除用户失败"),
    CHAT_GROUP_MESSAGE_HISTORY_PARSE_FAILED(70300, "聊天记录转化出错"),
    CHAT_GROUP_MESSAGE_HISTORY_IS_EMPTY(70301, "聊天记录为空"),
    DIALOG_IS_GENERATION(70400, "对话正在生成中,请稍后"),
    
    ENCRYPT_NO_SUCH_ALGORITHM(80000, "无此解密算法"),
    ENCRYPT_INVALID_PRI_KEY(80001, "私钥非法"),
    ENCRYPT_MSG_SIZE_INVALID(80002, "密文错误"),
    
    USER_COURSE_TERM_IS_NOT_VALID(100001, "用户数据异常，不存在指定课程"),
    ;

	private int code;
	private String message;

	private ReturnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	
	public String getMessage() {
	    return message;
	}

	public static ReturnCode getByName(String name) {
		try {
			return ReturnCode.valueOf(name);
		} catch (Exception e) {
			return UNKNOWN_ERROR;
		}
	}
}
