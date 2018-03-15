package com.yanzi.taurus.enums;

public enum ImageUploadSource {
    DEFAULT(0, "default/"), 
    USER_ICON(1, "user/icon/"), 
    DIALOG_IMAGE(2, "dialog/image/"), 
    DIALOG_CONTENT_IMAGE(3, "dialog/content/image/"), 
    CURRICULUM_IMAGE(4, "curriculum/image/"), 
    CURRICULUM_MAP(5, "curriculum/map/"),
    LESSON_ANALYSIS_IMAGE(6, "curriculum/lessonanalysis/image/"),
    QUESTION_IMAGE(6, "curriculum/question/image/"),
    QUESTION_ANALYSIS_IMAGE(7, "curriculum/questionanalysis/image/"),
    CURRICULUM_STORY_IMAGE(8, "curriculum/story/image/"),
    LESSON_IMAGE(9, "curriculum/lesson/image/"),
    LESSON_BACKGROUD_IMAGE(9, "curriculum/lessonbackgroud/image/"),
    LEVEL_IMAGE(11, "curriculum/level/image/"),
    QUESTION_ANSWER_IMAGE(11, "curriculum/questinanswer/image/"),
    SHARE_CURRICULUM(12, "share/curriculum/image/");

    private int source;
    private String prefix;

    private ImageUploadSource(int source, String prefix) {
        this.source = source;
        this.prefix = prefix;
    }

    public int getSource() {
        return source;
    }

    public String getPrefix() {
        return prefix;
    }

    public static ImageUploadSource getByName(String name) {
        try {
            return ImageUploadSource.valueOf(name);
        } catch (Exception e) {
            return DEFAULT;
        }
    }

    public static ImageUploadSource fromValue(Integer value) {
        try {
            return ImageUploadSource.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            return DEFAULT;
        }
    }
}
