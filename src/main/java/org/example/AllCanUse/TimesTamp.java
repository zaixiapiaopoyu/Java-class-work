package org.example.AllCanUse;

import java.util.Calendar;

public class TimesTamp {
    public static Times timestamp() {
        // 获取当前时间（包括年、月、日、时、分、秒）
        Calendar calendar = Calendar.getInstance();

        // 提取年、月、日、时、分、秒
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // 月份从0开始，所以需要+1
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时制
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        // 创建 Times 对象
        return new Times(year, month, day, hour, minute, second);
    }
}

