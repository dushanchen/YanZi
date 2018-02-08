package com.yanzi.taurus.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanzi.common.utils.HttpClientUtils;
import com.yanzi.common.utils.YanziResponseUtils;
import com.yanzi.taurus.entity.DialogInfo;

@Service
public class DialogService {

    @Value("#{configProperties['taurus.dialog.actor.url']}")
    private String loadUserActorDialogUrl = "";
    @Value("#{configProperties['taurus.dialog.reply.url']}")
    private String loadUserReplyDialogUrl = "";
    @Value("#{configProperties['taurus.dialog.like.url']}")
    private String loadUserLikeDialogUrl = "";

    @Autowired
    private HttpClientUtils httpClientUtils;

    public List<DialogInfo> loadUserActorDialogByUserId(long userId, int pageId, int limit) {
        return loadUserDialog(loadUserActorDialogUrl, userId, pageId, limit);
    }

    public List<DialogInfo> loadUserReplyDialogByUserId(long userId, int pageId, int limit) {
        return loadUserDialog(loadUserReplyDialogUrl, userId, pageId, limit);
    }

    public List<DialogInfo> loadUserLikeDialogByUserId(long userId, int pageId, int limit) {
        return loadUserDialog(loadUserLikeDialogUrl, userId, pageId, limit);
    }

    private List<DialogInfo> loadUserDialog(String urlfmt, long userId, int pageId, int limit) {
        String url = String.format(urlfmt, userId, pageId, limit);
        String response = httpClientUtils.getResponse(url);
        JSONObject responseJson = JSON.parseObject(response);
        if (!YanziResponseUtils.isValid(responseJson)) {
            return Collections.emptyList();
        }
        JSONObject message = YanziResponseUtils.getMessage(responseJson);
        JSONArray dialogJsonArray = message.getJSONArray("dialogs");
        if (null == dialogJsonArray || dialogJsonArray.isEmpty()) {
            return Collections.emptyList();
        }

        List<DialogInfo> result = new ArrayList<>();
        for (int i = 0; i < dialogJsonArray.size(); ++i) {
            JSONObject dialogJson = dialogJsonArray.getJSONObject(i);
            if (dialogJson.isEmpty()) {
                continue;
            }
            String dialogInfoStr = dialogJson.getString("dialogInfo");
            if (StringUtils.isEmpty(dialogInfoStr)) {
                continue;
            }
            DialogInfo dialogInfo = JSON.parseObject(dialogInfoStr, DialogInfo.class);
            result.add(dialogInfo);
        }
        return result;
    }
}
