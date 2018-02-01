package com.yanzi.cancer.view;

import java.util.List;

import com.yanzi.gemini.view.ViewResponseBase;

public class ViewDialogsResponse extends ViewResponseBase{
    private List<ViewDialogResponse> dialogs;

    public List<ViewDialogResponse> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<ViewDialogResponse> dialogs) {
        this.dialogs = dialogs;
    }
}