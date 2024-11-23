package org.example.AllCanUse;

import java.util.Calendar;

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
    public Times(int year,int month ,int day,int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String show(){
        return String.format("%d/%d/%d %02d:%02d:%02d",year,month ,day, hour,minute,second);
    }

    public String print() {
        return String.format("%d:%d:%d:%02d:%02d:%02d",year,month ,day, hour,minute,second);
    }

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

