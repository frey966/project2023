package com.example.shareSphere.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 查询某特定时间之前或之后的特定几个月的时间日期
 */
public class Demo7 {
    public static void main(String[] args) throws ParseException {

        //查询当前时间之后6个月时间
        int renewalsdata = 6;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        System.out.println(sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, renewalsdata);
        System.out.println(sdf.format(calendar.getTime()));
    }
}
