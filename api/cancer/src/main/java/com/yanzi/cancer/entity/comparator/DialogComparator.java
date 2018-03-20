package com.yanzi.cancer.entity.comparator;

import java.util.Comparator;
import com.yanzi.cancer.entity.DialogInfo;

public class DialogComparator implements Comparator<DialogInfo> {
    @Override
    public int compare(DialogInfo o1, DialogInfo o2) {
        return o1.getId() < o2.getId() ? 1 : -1;
    }
}
