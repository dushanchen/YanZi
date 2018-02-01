package com.yanzi.cancer.job.mysql;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yanzi.cancer.entity.DialogInfo;
import com.yanzi.cancer.mysql.DialogMapper;
import com.yanzi.cancer.service.DialogService;
import com.yanzi.gemini.emuns.DataSource;
import com.yanzi.gemini.job.MysqlLoadJob;
import com.yanzi.gemini.redis.GeminiCommentRedisDao;
import com.yanzi.gemini.redis.GeminiLikeRedisDao;
import com.yanzi.gemini.trace.MLogger;
import com.yanzi.gemini.utils.EnvUtils;

public class DialogIncrLoadJob extends MysqlLoadJob {
    @SuppressWarnings("unused")
    private static final Logger logger = new MLogger(DialogIncrLoadJob.class);

    @Autowired
    private DialogService dialogService;
    @Autowired
    private DialogMapper dialogMapper;
    @Autowired
    private GeminiCommentRedisDao geminiCommentRedisDao;
    @Autowired
    private GeminiLikeRedisDao geminiLikeRedisDao;
    @Autowired
    private EnvUtils envUtils;

    private Map<Long, DialogInfo> dialogInfoMap;

    @Override
    protected void beforeRun() {
        dialogInfoMap = dialogService.getDialogMap();
    }

    @Override
    protected void mysqlLoad() {
        List<DialogInfo> result = dialogMapper.selectTagsByRangTime(beginTime, endTime);
        if (null == result || result.isEmpty()) {
            return;
        }
        for (DialogInfo dialog : result) {
            if (dialog.getValid() > envUtils.getEnvValid().getValue()) {
                dialogInfoMap.remove(dialog.getId());
            } else {
                dialogInfoMap.put(dialog.getId(), dialog);
            }
        }
    }

    @Override
    protected void afterRun() {
        for (DialogInfo dialogInfo : dialogInfoMap.values()) {
            long commentCount = geminiCommentRedisDao
                    .getSourceCommentCount(DataSource.DIALOG.getSource(), dialogInfo.getId());
            dialogInfo.setCommentCount(commentCount);
            long godCommentCount = geminiCommentRedisDao
                    .getSourceGodCommentCount(DataSource.DIALOG.getSource(), dialogInfo.getId());
            dialogInfo.setGodCommentCount(godCommentCount);
            long likeCount = geminiLikeRedisDao.getSourceLikeCount(DataSource.DIALOG.getSource(),
                    dialogInfo.getId());
            dialogInfo.setLikeCount(likeCount);
        }
    }
}
