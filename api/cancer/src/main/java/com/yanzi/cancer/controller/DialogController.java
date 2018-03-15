package com.yanzi.cancer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanzi.cancer.controller.params.LoadDialogContentParams;
import com.yanzi.cancer.controller.params.LoadDialogInfoParams;
import com.yanzi.cancer.controller.params.LoadDialogsByIdsParams;
import com.yanzi.cancer.controller.params.LoadDialogsParams;
import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.cancer.service.DialogContentService;
import com.yanzi.cancer.service.DialogService;
import com.yanzi.cancer.view.ViewDialogContentResponse;
import com.yanzi.cancer.view.ViewDialogResponse;
import com.yanzi.cancer.view.ViewDialogsResponse;
import com.yanzi.gemini.controller.BaseController;
import com.yanzi.gemini.controller.converter.ResponseEntityWrapper;
import com.yanzi.gemini.view.ViewResponseBase;

@Controller
public class DialogController extends BaseController<ViewResponseBase> {

    @Autowired
    private DialogService dialogService;
    @Autowired
    private DialogContentService dialogContentService;

    @RequestMapping(value = "/load/dialoginfo", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadDialog(@Valid LoadDialogInfoParams params) {
        ViewDialogResponse dialogInfo = dialogService.getDialogById(params.getDialogId());
        return packageSuccessData(dialogInfo);
    }

    @RequestMapping(value = "/load/dialogs", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadDialogs(
            @Valid LoadDialogsParams params) {
        List<ViewDialogResponse> dialogs = dialogService.getDialogs(params.getPageId(), params.getLimit());
        ViewDialogsResponse response = new ViewDialogsResponse();
        response.setDialogs(dialogs);
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/load/dialogsbyids", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadDialogsByIds(
            @Valid LoadDialogsByIdsParams params) {
        List<ViewDialogResponse> dialogs = dialogService.getDialogsByIds(params.getDialogIds());
        ViewDialogsResponse response = new ViewDialogsResponse();
        response.setDialogs(dialogs);
        return packageSuccessData(response);
    }

    @RequestMapping(value = "/load/nextcontent", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public ResponseEntity<ResponseEntityWrapper> loadNextContent(
            @Valid LoadDialogContentParams params) {
       DialogContent content = dialogContentService.getNextContentByDialogIdAndContentId(params.getDialogId(), params.getContentId());
       return packageSuccessData(new ViewDialogContentResponse(content));
    }

}
