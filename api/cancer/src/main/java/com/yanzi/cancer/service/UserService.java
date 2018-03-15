package com.yanzi.cancer.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanzi.cancer.view.ViewDialogResponse;
import com.yanzi.cancer.view.ViewDialogsResponse;
import com.yanzi.gemini.emuns.DataSource;
import com.yanzi.gemini.emuns.UserSourceDataRelation;
import com.yanzi.gemini.redis.UserRedisDao;
import com.yanzi.gemini.utils.PageIndexCalUtil;

@Service
public class UserService {

    @Autowired
    private UserRedisDao userRedisDao;
    @Autowired
    private DialogService dialogService;
    @Autowired
    private DialogAuthorService dialogAuthorService;

    private List<Long> loadUserDialogRelation(long userId, int relation, int pageId, int limit) {
        int start = PageIndexCalUtil.getIndexBegin(pageId, limit);
        int end = start + limit - 1;
        List<Long> dialogIds = userRedisDao.getUserSourceDataRelation(userId, relation,
                DataSource.DIALOG.getSource(), start, end);
        if (null == dialogIds) {
            return Collections.emptyList();
        }
        return dialogIds;
    }

    private ViewDialogsResponse buildViewDialogsResponseByDialogIds(List<Long> dialogIds) {
        ViewDialogsResponse response = new ViewDialogsResponse();
        if (null == dialogIds || dialogIds.isEmpty()) {
            response.setDialogs(Collections.emptyList());
            return response;
        }
        long[] dialogIdArray = new long[dialogIds.size()];
        for (int i = 0; i < dialogIds.size(); ++i) {
            dialogIdArray[i] = dialogIds.get(i);
        }
        List<ViewDialogResponse> dialogs = dialogService.getDialogsByIds(dialogIdArray);
        response.setDialogs(dialogs);
        return response;
    }

    public ViewDialogsResponse loadUserLikeDialogs(long userId, int pageId, int limit) {
        List<Long> dialogIds = loadUserDialogRelation(userId,
                UserSourceDataRelation.LIKE.getRelation(), pageId, limit);
        return buildViewDialogsResponseByDialogIds(dialogIds);
    }

    public ViewDialogsResponse loadUserReplyDialogs(long userId, int pageId, int limit) {
        List<Long> dialogIds = loadUserDialogRelation(userId,
                UserSourceDataRelation.REPLY.getRelation(), pageId, limit);
        return buildViewDialogsResponseByDialogIds(dialogIds);
    }

    public ViewDialogsResponse loadUserDialogs(long userId, int pageId, int limit) {
        List<Long> allDialogIds = dialogAuthorService.getDialogIdsByUserId(userId);
        List<Long> dialogIds = PageIndexCalUtil.getPageResultList(allDialogIds, pageId, limit);
        return buildViewDialogsResponseByDialogIds(dialogIds);
    }
}
