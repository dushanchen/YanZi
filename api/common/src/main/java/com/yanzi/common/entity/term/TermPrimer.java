package com.yanzi.common.entity.term;

public class TermPrimer {

    public static final TermPrimer DEFAULT = new TermPrimer();

    private long termId;
    private String image;

    public long getTermId() {
        return termId;
    }

    public void setTermId(long termId) {
        this.termId = termId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
