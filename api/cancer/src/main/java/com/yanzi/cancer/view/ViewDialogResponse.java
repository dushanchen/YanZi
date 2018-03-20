package com.yanzi.cancer.view;

import java.util.Collection;
import java.util.List;

import com.yanzi.cancer.entity.DialogContent;
import com.yanzi.cancer.entity.DialogInfo;
import com.yanzi.gemini.entity.GeminiUserInfo;
import com.yanzi.gemini.view.ViewResponseBase;

public class ViewDialogResponse extends ViewResponseBase {
    private DialogInfo dialogInfo;
    private Collection<GeminiUserInfo> authors;
    private List<DialogContent> contents;

    public ViewDialogResponse(DialogInfo dialogInfo) {
        this.dialogInfo = dialogInfo;
    }

    public ViewDialogResponse(DialogInfo dialogInfo, Collection<GeminiUserInfo> authors) {
        this(dialogInfo);
        this.authors = authors;
    }

    public ViewDialogResponse(DialogInfo dialogInfo, Collection<GeminiUserInfo> authors,
            List<DialogContent> contents) {
        this(dialogInfo, authors);
        this.contents = contents;
    }

    public List<DialogContent> getContents() {
        return contents;
    }

    public void setContents(List<DialogContent> contents) {
        this.contents = contents;
    }

    public DialogInfo getDialogInfo() {
        return dialogInfo;
    }

    public void setDialogInfo(DialogInfo dialogInfo) {
        this.dialogInfo = dialogInfo;
    }

    public Collection<GeminiUserInfo> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<GeminiUserInfo> authors) {
        this.authors = authors;
    }

}
