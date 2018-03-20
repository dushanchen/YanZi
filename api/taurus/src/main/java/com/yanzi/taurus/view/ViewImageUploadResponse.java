package com.yanzi.taurus.view;

import com.yanzi.common.controller.view.ViewResponseBase;

public class ViewImageUploadResponse extends ViewResponseBase {
    private String token;
    private String name;
    private String urlPrefix;
    
    public ViewImageUploadResponse(String token, String name, String urlPrefix){
        this.token = token;
        this.name = name;
        this.urlPrefix = urlPrefix;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

}
