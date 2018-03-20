package com.yanzi.cancer.job.mysql;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.cancer.entity.DialogAuthor;
import com.yanzi.cancer.mysql.DialogAuthorMapper;
import com.yanzi.cancer.service.DialogAuthorService;
import com.yanzi.gemini.job.MysqlLoadJob;
import com.yanzi.gemini.trace.MLogger;
import com.yanzi.gemini.utils.EnvUtils;

public class DialogAuthorIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(DialogAuthorIncrLoadJob.class);

    @Autowired
    private DialogAuthorService dialogAuthorService;
    @Autowired
    private DialogAuthorMapper dialogAuthorMapper;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, Set<Long>> dialogAuthorIdMap;
    private Map<Long, Set<Long>> authorDialogIdMap;

    @Override
    protected void beforeRun() {
        dialogAuthorIdMap = dialogAuthorService.getDialogAuthorIdMap();
        authorDialogIdMap = dialogAuthorService.getAuthorDialogIdMap();
    }

    @Override
    protected void mysqlLoad() {
        List<DialogAuthor> result = dialogAuthorMapper.selectTagsByRangTime(beginTime, endTime);
        if (null == result || result.isEmpty()) {
            return;
        }
        for (DialogAuthor dialogAuthor : result) {
            Set<Long> authorSet = dialogAuthorIdMap.get(dialogAuthor.getDialogId());
            Set<Long> dialogSet = authorDialogIdMap.get(dialogAuthor.getUserId());
            if (dialogAuthor.getValid() > envUtils.getEnvValid().getValue()) {
                if (null != authorSet) {
                    authorSet.remove(dialogAuthor.getUserId());
                }
                if (null != dialogSet) {
                    dialogSet.remove(dialogAuthor.getDialogId());
                }
            } else {
                if (null == authorSet) {
                    authorSet = new HashSet<>();
                    dialogAuthorIdMap.put(dialogAuthor.getDialogId(), authorSet);
                }
                authorSet.add(dialogAuthor.getUserId());
                if (null == dialogSet) {
                    dialogSet = new HashSet<>();
                    authorDialogIdMap.put(dialogAuthor.getUserId(), dialogSet);
                }
                dialogSet.add(dialogAuthor.getDialogId());
            }
        }
    }
}
