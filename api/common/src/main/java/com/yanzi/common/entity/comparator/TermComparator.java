package com.yanzi.common.entity.comparator;

import java.util.Comparator;

import com.yanzi.common.entity.term.TermInfo;

public class TermComparator implements Comparator<TermInfo> {
    @Override
    public int compare(TermInfo o1, TermInfo o2) {
        if (o1.getStartTime() != o2.getEndTime()) {
            return o1.getStartTime() > o2.getStartTime() ? 1 : -1;
        }
        return o1.getIndex() > o2.getIndex() ? 1 : -1;
    }
}