package com.yanzi.taurus.view;

import java.util.List;

import com.yanzi.common.controller.view.ViewResponseBase;
import com.yanzi.taurus.entity.FeedbackInfo;

public class ViewFeedbackResponse extends ViewResponseBase{
    private List<FeedbackInfo> feedbacks;

    public List<FeedbackInfo> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackInfo> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
