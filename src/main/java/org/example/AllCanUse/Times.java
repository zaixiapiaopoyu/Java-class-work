package org.example.AllCanUse;

import java.util.Calendar;

/**
 * Times 类用于表示一个具体的时间点，包括年、月、日、时、分、秒。
 * 提供时间的格式化显示和时间戳计算功能。
 */

public class Times {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;


    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }

    /**
     * 构造函数，用于初始化时间对象。
     *
     * @param year   年份
     * @param month  月份
     * @param day    日期
     * @param hour   小时
     * @param minute 分钟
     * @param second 秒
     */
    public Times(int year,int month ,int day,int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * 格式化显示时间，格式为 "YYYY/MM/DD HH:mm:ss"。
     *
     * @return 格式化后的时间字符串
     */
    public String show(){
        return String.format("%d/%d/%d %02d:%02d:%02d",year,month ,day, hour,minute,second);
    }

    /**
     * 格式化显示时间，格式为 "YYYY:MM:DD:HH:mm:ss"。
     *
     * @return 格式化后的时间字符串
     */
    public String print() {
        return String.format("%d:%d:%d:%02d:%02d:%02d",year,month ,day, hour,minute,second);
    }

    /**
     * 计算当前时间对象距离 Unix 时间起点（1970-01-01 00:00:00）的毫秒数。
     *
     * @return 总毫秒数
     */
    public long TimeMilliseconds() {
        // 利用 Calendar 对象计算总毫秒数
        Calendar calendar = Calendar.getInstance();
        calendar.clear(); // 清空时间，避免意外干扰
        calendar.set(year, month - 1, day, hour, minute, second);

        // 计算从1970-01-01 00:00:00到当前时间的总毫秒数
        long totalMilliseconds = calendar.getTimeInMillis();
        return totalMilliseconds;
    }
}

