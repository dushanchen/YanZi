package com.yanzi.cancer.controller.params;

import com.yanzi.gemini.controller.validator.NotNull;

public class LoadDialogInfoParams {
    @NotNull
    private long dialogId;

    public long getDialogId() {
        return dialogId;
    }

    public void setDialogId(long dialogId) {
        this.dialogId = dialogId;
    }
}
