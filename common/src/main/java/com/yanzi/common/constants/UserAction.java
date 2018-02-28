package com.yanzi.common.constants;

public enum UserAction {
    UNKNOW_VALUE(-1), 
    LIKE(0),                    // 点赞
    COMMENT(1),                 // 评论
    ;

    private int action;

    private UserAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    } 

    public static UserAction getByName(String name) {
        try {
            return UserAction.valueOf(name);
        } catch (Exception e) {
            return UNKNOW_VALUE;
        }
    }
}
