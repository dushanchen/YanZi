package com.yanzi.common.constants.question;

import java.util.HashMap;
import java.util.Map;

public enum QuestionTextBlock {
    UNKNOW_VALUE(-1), 
    QUESTION_IMAGE(1), 
    QUESTION_TEXT(2), 
    ANSWER(3), 
    ANALYSIS_TEXT(4), 
    ANALYSIS_EXT_TEXT(5), 
    ANALYSIS_IMAGE(6), 
    DIALOG(7),
    INTRODUCE(8),
    ;

    private static Map<Integer, QuestionTextBlock> map = new HashMap<>();

    static {
        map.put(QUESTION_IMAGE.getBlock(), QUESTION_IMAGE);
        map.put(QUESTION_TEXT.getBlock(), QUESTION_TEXT);
        map.put(ANSWER.getBlock(), ANSWER);
        map.put(ANALYSIS_TEXT.getBlock(), ANALYSIS_TEXT);
        map.put(ANALYSIS_EXT_TEXT.getBlock(), ANALYSIS_EXT_TEXT);
        map.put(ANALYSIS_IMAGE.getBlock(), ANALYSIS_IMAGE);
        map.put(DIALOG.getBlock(), DIALOG);
        map.put(INTRODUCE.getBlock(), INTRODUCE);
    }

    private int block;

    private QuestionTextBlock(int block) {
        this.block = block;
    }

    public int getBlock() {
        return block;
    }

    public static QuestionTextBlock getByBlock(int block) {
        QuestionTextBlock questionTextBlock = map.get(block);
        if (null == questionTextBlock) {
            return UNKNOW_VALUE;
        }
        return questionTextBlock;
    }
}