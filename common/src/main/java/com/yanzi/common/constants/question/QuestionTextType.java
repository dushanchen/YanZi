package com.yanzi.common.constants.question;

import java.util.HashMap;
import java.util.Map;

public enum QuestionTextType {
    UNKNOW_VALUE(-1), 
    TEXT_STYLE_NORMAL(1001),                    // 普通
    TEXT_STYLE_TITLE(1002),                    // 标题
    TEXT_STYLE_BOLD(1003),                    // 加粗
    TEXT_STYLE_PRE_VERTICAL(1004),                    // 前竖线  
    TEXT_STYLE_PRINCIPLE(1005),            // 原理
    IMAGE_STYLE_1(2001),                   // 普通图片
    ANSWER_STYLE_1(3001), 
    ANSWER_STYLE_2(3002), 
    ANSWER_STYLE_3(3003), 
    ANSWER_STYLE_4(3004), 
    DIALOG_STYLE_1(4001),
    BLANK_STYLE_1(5001),
    ;
    private static Map<Integer, QuestionTextType> map = new HashMap<>();

    static {
        map.put(TEXT_STYLE_TITLE.getType(), TEXT_STYLE_TITLE);
        map.put(TEXT_STYLE_NORMAL.getType(), TEXT_STYLE_NORMAL);
        map.put(TEXT_STYLE_BOLD.getType(), TEXT_STYLE_BOLD);
        map.put(TEXT_STYLE_PRE_VERTICAL.getType(), TEXT_STYLE_PRE_VERTICAL);
        map.put(TEXT_STYLE_PRINCIPLE.getType(), TEXT_STYLE_PRINCIPLE);
        map.put(BLANK_STYLE_1.getType(), BLANK_STYLE_1);
        map.put(IMAGE_STYLE_1.getType(), IMAGE_STYLE_1);
        map.put(ANSWER_STYLE_1.getType(), ANSWER_STYLE_1);
        map.put(ANSWER_STYLE_2.getType(), ANSWER_STYLE_2);
        map.put(ANSWER_STYLE_3.getType(), ANSWER_STYLE_3);
        map.put(ANSWER_STYLE_4.getType(), ANSWER_STYLE_4);
        map.put(DIALOG_STYLE_1.getType(), DIALOG_STYLE_1);
    }

    private int type;

    private QuestionTextType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static QuestionTextType getByType(int type) {
        QuestionTextType questionTextType = map.get(type);
        if (null == questionTextType) {
            return UNKNOW_VALUE;
        }
        return questionTextType;
    }
}
