package com.yanzi.common.constants.question;

import java.util.HashMap;
import java.util.Map;

public enum QuestionType {
    UNKNOW_VALUE(-1), CLOZE(1), CHOISE(2), MATCH(3), DIALOG(4),;

    private static Map<Integer, QuestionType> map;

    static {
        map = new HashMap<Integer, QuestionType>();
        map.put(CLOZE.getType(), CLOZE);
        map.put(CHOISE.getType(), CHOISE);
        map.put(MATCH.getType(), MATCH);
        map.put(DIALOG.getType(), DIALOG);
    }

    private int type;

    private QuestionType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static QuestionType getByName(String name) {
        try {
            return QuestionType.valueOf(name);
        } catch (Exception e) {
            return UNKNOW_VALUE;
        }
    }

    public static QuestionType getByType(int type) {
        QuestionType questionType = map.get(type);
        if (null == questionType) {
            return QuestionType.UNKNOW_VALUE;
        }
        return questionType;
    }
}
