package com.common.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: com.common.util
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/4/11
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/4/11
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
public class DateUtil {

    //获取周第一天
    public static Date getStartDayOfWeek(String date) {
        LocalDate now = LocalDate.parse(date);
        return getStartDayOfWeek(now);
    }

    public static Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate.with(fieldISO, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    //获取周最后一天
    public static Date getEndDayOfWeek(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return getEndDayOfWeek(localDate);
    }

    public static Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate.with(fieldISO, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }
    //一天的开始
    public static Date getStartOfDay(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return getStartOfDay(localDate);
    }

    public static Date getStartOfDay(TemporalAccessor date) {
        LocalDate localDate = LocalDate.from(date);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    //一天的结束
    public static Date getEndOfDay(String date){
        LocalDate localDate = LocalDate.parse(date);
        return getEndOfDay(localDate);
    }
    public static Date getEndOfDay(TemporalAccessor date) {
        LocalDate localDate = LocalDate.from(date);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }
}
