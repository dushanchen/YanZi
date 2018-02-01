package com.yanzi.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.yanzi.common.trace.MLogger;

public class PageIndexCalUtil {
    private static final Logger logger = new MLogger(PageIndexCalUtil.class);
    
    public static final int FIRST_PAGE_ID = 1;

    public static int getIndexBegin(int pageId, int pageSize) {
        return (pageId - 1) * pageSize;
    }

    public static int getIndexEnd(int indexBegin, int pageSize, int allSize) {
        int indexEnd = indexBegin + pageSize < allSize ? indexBegin + pageSize : allSize;
        if (indexEnd < indexBegin) {
            indexEnd = indexBegin;
        }
        return indexEnd;
    }

    private static <T> List<T> getPartList(List<T> list, int indexBegin, int indexEnd) {
        try {
            return list.subList(indexBegin, indexEnd);
        } catch (IndexOutOfBoundsException e) {
            logger.warn("no more videos.");
            return new ArrayList<T>();
            // throw new PageVideoOutOfBoundsException(list.size());
        }
    }

    public static <T> List<T> getPageResultList(List<T> allResult, int pageId, int rltnum) {
        if (allResult == null) {
            return new ArrayList<T>();
        }
        int allSize = allResult.size();
        if (allSize == 0) {
            return allResult;
        }
        int fromIndex = PageIndexCalUtil.getIndexBegin(pageId, rltnum);
        int toIndex = PageIndexCalUtil.getIndexEnd(fromIndex, rltnum, allSize);
        return PageIndexCalUtil.getPartList(allResult, fromIndex, toIndex);
    }
}
