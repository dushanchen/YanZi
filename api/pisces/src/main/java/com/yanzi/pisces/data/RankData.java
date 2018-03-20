package com.yanzi.pisces.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.yanzi.pisces.entity.RankInfo;
import com.yanzi.pisces.entity.comparator.RankEntityCompartor;

/**
 * 以后执行
 * @author zsong
 *
 */
@Deprecated
@Service
public class RankData {
    // id -> rankInfo
    private Map<Long, RankInfo> rankMap = new ConcurrentHashMap<>();
    // courseId -> termId -> userId -> rankInfo
    private Map<Long, Map<Long, Map<Long, RankInfo>>> courseTermRankMap = new ConcurrentHashMap<>();
    // id -> rankInfo
    private Map<Long, RankInfo> weekRankMap = new ConcurrentHashMap<>();
    // courseId -> termId -> userId -> rankInfo
    private Map<Long, Map<Long, Map<Long, RankInfo>>> courseTermWeekRankMap = new ConcurrentHashMap<>();

    private List<RankInfo> loadUserFirendRankListBase(long userId, List<Long> friendIdList,
            Map<Long, RankInfo> map) {
        List<RankInfo> friendRankList = new ArrayList<>();
        for (long friendId : friendIdList) {
            RankInfo rankEntity = map.get(friendId);
            if (null != rankEntity) {
                friendRankList.add(rankEntity);
            }
        }
        RankInfo userRankEntity = map.get(userId);
        if (null == userRankEntity) {
            userRankEntity = new RankInfo();
            userRankEntity.setUserId(userId);
        }
        friendRankList.add(userRankEntity);
        RankEntityCompartor compartor = new RankEntityCompartor();
        Collections.sort(friendRankList, compartor);
        List<RankInfo> result = new ArrayList<>();
        int realRank = 0;
        int rank = realRank;
        long lastExp = Long.MAX_VALUE;
        for (RankInfo rankEntity : friendRankList) {
            ++realRank;
            RankInfo newRank = new RankInfo();
            newRank.setExp(rankEntity.getExp());
            newRank.setUserId(rankEntity.getUserId());
            if (rankEntity.getExp() != lastExp) {
                rank = realRank;
            }
            newRank.setRank(rank);
            result.add(newRank);
            lastExp = rankEntity.getExp();
        }
        return result;
    }

    public List<RankInfo> loadUserFirendRankList(long userId, List<Long> friendIdList) {
        return this.loadUserFirendRankListBase(userId, friendIdList, rankMap);
    }

    public List<RankInfo> loadUserFirendWeekRankList(long userId, List<Long> friendIdList) {
        return this.loadUserFirendRankListBase(userId, friendIdList, weekRankMap);
    }

    public int loadUserFriendRankValue(long userId, List<Long> friendIdList) {
        List<RankInfo> friendRankList = loadUserFirendRankList(userId, friendIdList);
        for (RankInfo rankEntity : friendRankList) {
            if (rankEntity.getUserId() == userId) {
                return rankEntity.getRank();
            }
        }
        return 0;
    }

    private List<RankInfo> loadUserCourseTermRankListBase(long userId, long courseId, long termId,
            Map<Long, Map<Long, Map<Long, RankInfo>>> map) {
        Map<Long, Map<Long, RankInfo>> termRankMap = map.get(courseId);
        if (null == termRankMap) {
            return Collections.emptyList();
        }
        Map<Long, RankInfo> userRankMap = termRankMap.get(termId);
        if (userRankMap == null) {
            return Collections.emptyList();
        }
        List<RankInfo> rankInfos = new ArrayList<>(userRankMap.values());
        RankEntityCompartor compartor = new RankEntityCompartor();
        Collections.sort(rankInfos, compartor);
        return rankInfos;
    }

    public List<RankInfo> loadUserCourseTermRankList(long userId, long courseId, long termId) {
        return loadUserCourseTermRankListBase(userId, courseId, termId, courseTermRankMap);
    }

    public List<RankInfo> loadUserCourseTermWeekRankList(long userId, long courseId, long termId) {
        return loadUserCourseTermRankListBase(userId, courseId, termId, courseTermWeekRankMap);
    }

    public int loadUserCourseTermRankValue(long userId, long courseId, long termId) {
        Map<Long, Map<Long, RankInfo>> termRankMap = courseTermRankMap.get(courseId);
        if (null == termRankMap) {
            return 0;
        }
        Map<Long, RankInfo> userRankMap = termRankMap.get(termId);
        if (userRankMap == null) {
            return 0;
        }
        RankInfo rank = userRankMap.get(userId);
        if (null == rank) {
            return 0;
        }
        return rank.getRank();
    }

    public Map<Long, RankInfo> getRankMap() {
        return rankMap;
    }

    public void setRankMap(Map<Long, RankInfo> rankMap) {
        this.rankMap = rankMap;
    }

    public Map<Long, Map<Long, Map<Long, RankInfo>>> getCourseTermRankMap() {
        return courseTermRankMap;
    }

    public void setCourseTermRankMap(Map<Long, Map<Long, Map<Long, RankInfo>>> courseTermRankMap) {
        this.courseTermRankMap = courseTermRankMap;
    }

    public Map<Long, RankInfo> getWeekRankMap() {
        return weekRankMap;
    }

    public void setWeekRankMap(Map<Long, RankInfo> weekRankMap) {
        this.weekRankMap = weekRankMap;
    }

    public Map<Long, Map<Long, Map<Long, RankInfo>>> getCourseTermWeekRankMap() {
        return courseTermWeekRankMap;
    }

    public void setCourseTermWeekRankMap(
            Map<Long, Map<Long, Map<Long, RankInfo>>> courseTermWeekRankMap) {
        this.courseTermWeekRankMap = courseTermWeekRankMap;
    }

}
