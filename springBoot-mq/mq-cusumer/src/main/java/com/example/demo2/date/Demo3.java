package com.example.shareSphere.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 比较量两个时间的大小
 */
public class Demo3 {

    public static void main(String[] args) throws ParseException {
        //两个日期比较
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = df.parse("2019-02-03");
        //获取当前时间
        Date dt2 = new Date();
        if (dt1.getTime() > dt2.getTime()) {
            System.out.println(df.format(dt1.getTime()) + "比较大");
        } else if (dt1.getTime() < dt2.getTime()) {
            System.out.println(df.format(dt2.getTime()) + "是当前时间，当前时间比较大");
        }
        if (dt1.getTime() == dt2.getTime()) {
            System.out.println("两个时间一样");
        }
    }
}
