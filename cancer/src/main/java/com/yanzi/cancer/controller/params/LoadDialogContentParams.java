package com.yanzi.cancer.controller.params;

import com.yanzi.gemini.controller.validator.NotNull;

public class LoadDialogContentParams {
    @NotNull
    private long dialogId;
    @NotNull
    private long contentId;

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }
}
