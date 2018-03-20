package com.yanzi.cancer.entity.comparator;

import java.util.Comparator;

import com.yanzi.cancer.entity.DialogContent;

public class DialogContentComparator implements Comparator<DialogContent> {
    @Override
    public int compare(DialogContent o1, DialogContent o2) {
        return o1.getId() > o2.getId() ? 1 : -1;
    }
}
