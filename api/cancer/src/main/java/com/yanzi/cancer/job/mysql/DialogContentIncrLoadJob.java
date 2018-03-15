package com.yanzi.cancer.job.mysql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.cancer.entity.comparator.DialogContentComparator;
import com.yanzi.cancer.mysql.DialogContentMapper;
import com.yanzi.cancer.service.DialogContentService;
import com.yanzi.gemini.emuns.DataSource;
import com.yanzi.gemini.job.MysqlLoadJob;
import com.yanzi.gemini.redis.GeminiCommentRedisDao;
import com.yanzi.gemini.trace.MLogger;
import com.yanzi.gemini.utils.EnvUtils;

public class DialogContentIncrLoadJob extends MysqlLoadJob implements InitializingBean {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(DialogContentIncrLoadJob.class);

    @Autowired
    private DialogContentService dialogContentService;
    @Autowired
    private DialogContentMapper dialogContentMapper;
    @Autowired
    private GeminiCommentRedisDao geminiCommentRedisDao;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, List<DialogContent>> dialogContentMap;
    private Map<Long, List<DialogContent>> newMap;

    private DialogContentComparator comparator;

    @Override
    public void afterPropertiesSet() throws Exception {
        comparator = new DialogContentComparator();
    }

    @Override
    protected void beforeRun() {
        dialogContentMap = dialogContentService.getDialogContentMap();
        newMap = new HashMap<>();
    }

    @Override
    protected void afterRun() {
        for (Entry<Long, List<DialogContent>> entry : newMap.entrySet()) {
            long dialogId = entry.getKey();
            List<DialogContent> contentList = entry.getValue();
            Collections.sort(contentList, comparator);
            for (DialogContent dialogContent : contentList) {
                long commentCount = geminiCommentRedisDao.getSourceCommentCount(
                        DataSource.DIALOG_CONTENT.getSource(), dialogContent.getId());
                dialogContent.setCommentCount(commentCount);
            }
            dialogContentMap.put(dialogId, contentList);
        }
    }

    @Override
    protected void mysqlLoad() {
        List<DialogContent> result = dialogContentMapper.selectTagsByRangTime(beginTime, endTime);
        if (null == result || result.isEmpty()) {
            return;
        }
        for (DialogContent content : result) {
            long dialogId = content.getDialogId();
            List<DialogContent> contentList = newMap.get(dialogId);
            if (null == contentList) {
                contentList = new ArrayList<>();
                List<DialogContent> oldList = dialogContentMap.get(dialogId);
                if (oldList != null) {
                    contentList.addAll(oldList);
                }
                newMap.put(dialogId, contentList);
            }
            for (int i = 0; i < contentList.size(); ++i) {
                if (contentList.get(i).getId() == content.getId()) {
                    contentList.remove(i);
                    break;
                }
            }
            if (content.getValid() > envUtils.getEnvValid().getValue()) {
                continue;
            }
            contentList.add(content);
        }
    }
}
