package com.yanzi.cancer.view;

import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.gemini.view.ViewResponseBase;

public class ViewDialogContentResponse extends ViewResponseBase {
    private DialogContent content;

    public ViewDialogContentResponse(DialogContent content){
        this.content = content;
    }
    public DialogContent getContent() {
        return content;
    }

    public void setContent(DialogContent content) {
        this.content = content;
    }
}
