package com.yanzi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static final long MILLIS_PER_DAY = 86400000l;
    
    public static Date getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static com.yanzi.common.entity.Date getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(cal.getTime());
        String week = String.format("%d%02d",cal.getWeekYear(), cal.get(Calendar.WEEK_OF_YEAR));
        cal.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, 2);
        String tomorrow = dateFormat.format(cal.getTime());
        com.yanzi.common.entity.Date dateInfo = new com.yanzi.common.entity.Date();
        dateInfo.setDay(today);
        dateInfo.setLastDay(yesterday);
        dateInfo.setNextDay(tomorrow);
        dateInfo.setWeek(week);
        return dateInfo;
    }
}
