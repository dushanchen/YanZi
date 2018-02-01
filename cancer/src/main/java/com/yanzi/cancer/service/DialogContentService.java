package com.yanzi.cancer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.gemini.trace.MLogger;

@Service
public class DialogContentService {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(DialogContentService.class);

    private Map<Long, List<DialogContent>> dialogContentMap = new ConcurrentHashMap<>();

    public List<DialogContent> getContentsByDialogId(long dialogId) {
        List<DialogContent> result = dialogContentMap.get(dialogId);
        if(null == result) {
            result = new ArrayList<>();
        }
        return result;
    }

    public DialogContent getNextContentByDialogIdAndContentId(long dialogId, long contentId) {
        List<DialogContent> dialogContentList = dialogContentMap.get(dialogId);
        if(null == dialogContentList) {
            return new DialogContent();
        }
        for(DialogContent dialogContent : dialogContentList) {
            if(dialogContent.getId() > contentId) {
                return dialogContent;
            }
        }
        return new DialogContent();
    }

    public Map<Long, List<DialogContent>> getDialogContentMap() {
        return dialogContentMap;
    }

    public void setDialogContentMap(Map<Long, List<DialogContent>> dialogContentMap) {
        this.dialogContentMap = dialogContentMap;
    }
}
