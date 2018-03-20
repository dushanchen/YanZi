package com.yanzi.taurus.controller.params;
 
import com.yanzi.common.controller.params.UserActionParamsBase;
import com.yanzi.common.controller.validator.NotNull;
import com.yanzi.taurus.enums.ImageUploadSource;

public class ImageUploadParams extends UserActionParamsBase {
    @NotNull
    private ImageUploadSource source = ImageUploadSource.DEFAULT;

    public ImageUploadSource getSource() {
        return source;
    }

    public void setSource(ImageUploadSource source) {
        this.source = source;
    }

}
