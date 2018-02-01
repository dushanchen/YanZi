package com.yanzi.cancer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.gemini.entity.GeminiUserInfo;
import com.yanzi.gemini.service.GeminiUserService;
import com.yanzi.gemini.trace.MLogger;

@Service
public class DialogAuthorService {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(DialogAuthorService.class);

    private Map<Long, Set<Long>> dialogAuthorIdMap = new ConcurrentHashMap<>();
    private Map<Long, Set<Long>> authorDialogIdMap = new ConcurrentHashMap<>();

    @Autowired
    private GeminiUserService geminiUserService;
    @Autowired
    private DialogService dialogService;

    public Collection<GeminiUserInfo> getAuthorsByIds(Collection<Long> authorIds) {
        if (null == authorIds || authorIds.isEmpty()) {
            return new ArrayList<>();
        }
        return geminiUserService.getUserInfoById(new ArrayList<>(authorIds));
    }

    public Collection<GeminiUserInfo> getAuthorsByDialogId(long dialogId) {
        Set<Long> authorIdSet = dialogAuthorIdMap.get(dialogId);
        if (null == authorIdSet || authorIdSet.isEmpty()) {
            return new ArrayList<>();
        }
        return this.getAuthorsByIds(authorIdSet);
    }

    public List<Long> getDialogIdsByUserId(long userId) {
        Set<Long> dialogIdSet = this.authorDialogIdMap.get(userId);
        if (null == dialogIdSet) {
            return Collections.emptyList();
        }
        List<Long> dialogIds = new ArrayList<>();
        for(long dialogId:dialogIdSet) {
            if(dialogService.dialogIsExist(dialogId)) {
                dialogIds.add(dialogId);
            }
        }
        if(dialogIds.isEmpty()) {
            return Collections.emptyList();
        }
        Collections.sort(dialogIds, Collections.reverseOrder());
        return dialogIds;
    }

    public Map<Long, Set<Long>> getDialogAuthorIdMap() {
        return dialogAuthorIdMap;
    }

    public void setDialogAuthorIdMap(Map<Long, Set<Long>> dialogAuthorIdMap) {
        this.dialogAuthorIdMap = dialogAuthorIdMap;
    }

    public Map<Long, Set<Long>> getAuthorDialogIdMap() {
        return authorDialogIdMap;
    }

    public void setAuthorDialogIdMap(Map<Long, Set<Long>> authorDialogIdMap) {
        this.authorDialogIdMap = authorDialogIdMap;
    }

}
