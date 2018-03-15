package com.yanzi.cancer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.cancer.entity.DialogInfo;
import com.yanzi.cancer.entity.comparator.DialogComparator;
import com.yanzi.cancer.view.ViewDialogResponse;
import com.yanzi.gemini.entity.GeminiUserInfo;
import com.yanzi.gemini.utils.PageIndexCalUtil;

@Service
public class DialogService implements InitializingBean {
    private Map<Long, DialogInfo> dialogMap = new ConcurrentHashMap<>();

    private DialogComparator dialogComparator;

    @Autowired
    private DialogAuthorService dialogAuthorService;
    @Autowired
    private DialogContentService dialogContentService;

    @Override
    public void afterPropertiesSet() throws Exception {
        dialogComparator = new DialogComparator();
    }

    public List<ViewDialogResponse> getDialogs(int pageId, int limit) {
        List<DialogInfo> allDialogList = new ArrayList<>();
        if (!dialogMap.isEmpty()) {
            allDialogList.addAll(dialogMap.values());
            Collections.sort(allDialogList, dialogComparator);
        }
        List<DialogInfo> pageDialogInfoList = PageIndexCalUtil.getPageResultList(allDialogList,
                pageId, limit);
        List<ViewDialogResponse> result = new ArrayList<>();
        for (DialogInfo dialogInfo : pageDialogInfoList) {
            ViewDialogResponse response = getDialog(dialogInfo);
            result.add(response);
        }
        return result;
    }

    public List<ViewDialogResponse> getDialogsByIds(long[] dialogIds) {
        List<ViewDialogResponse> result = new ArrayList<>();
        if (dialogMap.isEmpty()) {
            return result;
        }
        for (long dialogId : dialogIds) {
            DialogInfo dialogInfo = dialogMap.get(dialogId);
            ViewDialogResponse response = getDialog(dialogInfo);
            if (null != response) {
                result.add(response);
            }
        }
        return result;
    }

    private ViewDialogResponse getDialog(DialogInfo dialogInfo) {
        if (null == dialogInfo || dialogInfo.getId() == 0) {
            return null;
        }
        ViewDialogResponse response = new ViewDialogResponse(dialogInfo);
        Collection<GeminiUserInfo> authors = dialogAuthorService
                .getAuthorsByDialogId(dialogInfo.getId());
        response.setAuthors(authors);
        return response;
    }

    @Deprecated
    public ViewDialogResponse getFullDialogById(long id) {
        ViewDialogResponse response = this.getDialogById(id);
        List<DialogContent> contents = dialogContentService.getContentsByDialogId(id);
        response.setContents(contents);
        return response;
    }

    public ViewDialogResponse getDialogById(long id) {
        DialogInfo dialogInfo = dialogMap.get(id);
        if (null == dialogInfo) {
            return new ViewDialogResponse(new DialogInfo());
        }
        Collection<GeminiUserInfo> authors = dialogAuthorService.getAuthorsByDialogId(id);
        return new ViewDialogResponse(dialogInfo, authors);
    }

    public boolean dialogIsExist(long id) {
        return dialogMap.containsKey(id);
    }

    public Map<Long, DialogInfo> getDialogMap() {
        return dialogMap;
    }

    public void setDialogMap(Map<Long, DialogInfo> dialogMap) {
        this.dialogMap = dialogMap;
    }

}
