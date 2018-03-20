package com.yanzi.common.entity.college.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yanzi.common.constants.question.QuestionTextBlock;
import com.yanzi.common.constants.question.QuestionTextType;
import com.yanzi.common.entity.college.question.text.AnswerStyle1;
import com.yanzi.common.entity.college.question.text.AnswerStyle2;
import com.yanzi.common.entity.college.question.text.AnswerStyle3;
import com.yanzi.common.entity.college.question.text.AnswerStyle4;
import com.yanzi.common.entity.college.question.text.BlankStyle;
import com.yanzi.common.entity.college.question.text.DialogStyle1;
import com.yanzi.common.entity.college.question.text.ImageStyle1;
import com.yanzi.common.entity.college.question.text.StyleBase;
import com.yanzi.common.entity.college.question.text.TextStyle;
import com.yanzi.common.entity.comparator.QuestionTextComparator;

public class QuestionInfo {
    public static final QuestionInfo DEFAULT = new QuestionInfo();

    private long id;
    private long lessonId;
    private int type;
    private int index;
    private int valid;
    private String jsonContent;


	private List<StyleBase> questionImages = new ArrayList<>();
    
    private List<StyleBase> questionTexts = new ArrayList<>();

    private List<StyleBase> answers = new ArrayList<>();

    private List<StyleBase> analysisTexts = new ArrayList<>();

    private List<StyleBase> analysisExtTexts = new ArrayList<>();

    private List<StyleBase> analysisImages = new ArrayList<>();

    public List<StyleBase> getIntroduce() {
		return introduce;
	}

	public void setIntroduce(List<StyleBase> introduce) {
		this.introduce = introduce;
	}

	private List<StyleBase> dialogs = new ArrayList<>();
    
    private List<StyleBase> introduce = new ArrayList<>();
    

    public void build(List<QuestionTextInfo> textInfoList) {
        QuestionTextComparator comparator = new QuestionTextComparator();
        Collections.sort(textInfoList, comparator);

        for (QuestionTextInfo textInfo : textInfoList) {
            switch (QuestionTextBlock.getByBlock(textInfo.getBlock())) {
                case QUESTION_IMAGE://判空
                    buildQuestionImage(textInfo);
                    break;
                case QUESTION_TEXT:
                    buildQuestionText(textInfo);
                    break;
                case ANSWER:
                    buildQuestionAnswer(textInfo);
                    break;
                case ANALYSIS_TEXT:
                    buildAnalysisText(textInfo);
                    break;
                case ANALYSIS_EXT_TEXT:
                    buildAnalysisExtText(textInfo);
                    break;
                case ANALYSIS_IMAGE://判空
                    buildAnalysisImage(textInfo);
                    break;
                case DIALOG:
                    buildDialgs(textInfo);
                    break;
                case INTRODUCE:
                	buildIntroduce(textInfo);
                default:
                    break;
            }
        }
    }

    private void buildIntroduce(QuestionTextInfo textInfo) {
    	switch (QuestionTextType.getByType(textInfo.getType())) {
        case INTRODUCE:
        	introduce.add(new TextStyle(textInfo));
            break;
        default:
            break;
    	}
	}
    
    private void buildQuestionImage(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case IMAGE_STYLE_1:
            	if(!textInfo.getImage().isEmpty())
            		questionImages.add(new ImageStyle1(textInfo));//判空
                break;
            default:
                break;
        }
    }
    private void buildQuestionText(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case TEXT_STYLE_NORMAL:
            case TEXT_STYLE_TITLE:
            case TEXT_STYLE_BOLD:
            case TEXT_STYLE_PRE_VERTICAL:
            case TEXT_STYLE_PRINCIPLE:
                questionTexts.add(new TextStyle(textInfo));
                break;
            case BLANK_STYLE_1:
                questionTexts.add(new BlankStyle(textInfo));
                break;
            default:
                break;
        }
    }

    private void buildQuestionAnswer(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case ANSWER_STYLE_1:
                answers.add(new AnswerStyle1(textInfo));
                break;
            case ANSWER_STYLE_2:
                answers.add(new AnswerStyle2(textInfo));
                break;
            case ANSWER_STYLE_3:
                answers.add(new AnswerStyle3(textInfo));
                break;
            default:
                break;
        }
    }

    private void buildAnalysisText(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case TEXT_STYLE_NORMAL:
            case TEXT_STYLE_TITLE:
            case TEXT_STYLE_BOLD:
            case TEXT_STYLE_PRE_VERTICAL:
            case TEXT_STYLE_PRINCIPLE:
                analysisTexts.add(new TextStyle(textInfo));
                break;
            default:
                break;
        }
    }

    private void buildAnalysisExtText(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case TEXT_STYLE_NORMAL:
            case TEXT_STYLE_TITLE:
            case TEXT_STYLE_BOLD:
            case TEXT_STYLE_PRE_VERTICAL:
            case TEXT_STYLE_PRINCIPLE:
                analysisExtTexts.add(new TextStyle(textInfo));
                break;
            default:
                break;
        }
    }

    private void buildAnalysisImage(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case IMAGE_STYLE_1://判空
            	if(!textInfo.getImage().isEmpty())
            		analysisImages.add(new ImageStyle1(textInfo));
                break;
            default:
                break;
        }
    }

    private void buildDialgs(QuestionTextInfo textInfo) {
        switch (QuestionTextType.getByType(textInfo.getType())) {
            case TEXT_STYLE_TITLE:
                questionTexts.add(new TextStyle(textInfo));
                break;
            case DIALOG_STYLE_1:
                long parentId = textInfo.getParentId();
                DialogStyle1 dialog = new DialogStyle1(textInfo);
                if (parentId != 0) {
                    for (StyleBase style : dialogs) {
                        if (style instanceof DialogStyle1) {
                            DialogStyle1 existDialog = (DialogStyle1) style;
                            List<AnswerStyle4> answers = existDialog.getAnswers();
                            if (null != answers && !answers.isEmpty()) {
                                for (AnswerStyle4 answer : answers) {
                                    if (answer.getId() == parentId) {
                                        answer.getDialogs().add(dialog);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    dialogs.add(dialog);
                    return;
                }
                break;
            case ANSWER_STYLE_4:
                AnswerStyle4 answer = new AnswerStyle4(textInfo);
                for (StyleBase style : dialogs) {
                    if (style instanceof DialogStyle1) {
                        DialogStyle1 existDialog = (DialogStyle1) style;
                        if (existDialog.getId() == textInfo.getParentId()) {
                            existDialog.getAnswers().add(answer);
                            return;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public List<StyleBase> getQuestionImages() {
        return questionImages;
    }

    public void setQuestionImages(List<StyleBase> questionImages) {
        this.questionImages = questionImages;
    }

    public List<StyleBase> getQuestionTexts() {
        return questionTexts;
    }

    public void setQuestionTexts(List<StyleBase> questionTexts) {
        this.questionTexts = questionTexts;
    }

    public List<StyleBase> getAnswers() {
        return answers;
    }

    public void setAnswers(List<StyleBase> answers) {
        this.answers = answers;
    }

    public List<StyleBase> getAnalysisTexts() {
        return analysisTexts;
    }

    public void setAnalysisTexts(List<StyleBase> analysisTexts) {
        this.analysisTexts = analysisTexts;
    }

    public List<StyleBase> getAnalysisExtTexts() {
        return analysisExtTexts;
    }

    public void setAnalysisExtTexts(List<StyleBase> analysisExtTexts) {
        this.analysisExtTexts = analysisExtTexts;
    }

    public List<StyleBase> getAnalysisImages() {
        return analysisImages;
    }

    public void setAnalysisImages(List<StyleBase> analysisImages) {
        this.analysisImages = analysisImages;
    }

    public List<StyleBase> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<StyleBase> dialogs) {
        this.dialogs = dialogs;
    }

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}
    
}