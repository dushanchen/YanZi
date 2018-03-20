package com.yanzi.pisces.entity.comparator;

import java.util.Comparator;
import com.yanzi.pisces.entity.RankInfo;

public class RankEntityCompartor implements Comparator<RankInfo> {
    @Override
    public int compare(RankInfo o1, RankInfo o2) {
        return o1.getExp() < o2.getExp() ? 1 : -1;
    }
}
