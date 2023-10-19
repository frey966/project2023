package com.example.shareSphere.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期与周的转换
 */
public class Demo8 {

    public static void main(String[] args) throws ParseException {

//星期转换
        Date time = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);

        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        cal.setTime(time);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        System.out.println("weekDays[w]===="+weekDays[w]);
        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow); // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);
    }
}
