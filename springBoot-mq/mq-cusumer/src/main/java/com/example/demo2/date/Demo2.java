package com.example.shareSphere.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取某一年中某一周的周几的时间
 */
public class Demo2 {

    public static void main(String[] args) throws ParseException {
        //计算某一年中的第多少周的周一和周日的日期时间
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019); // 2019年
        cal.set(Calendar.WEEK_OF_YEAR, 53); // 设置为2019年的第33周
        cal.set(Calendar.DAY_OF_WEEK, 2); // 1表示周日，2表示周一，7表示周六
        Date date = cal.getTime();

        cal.set(Calendar.YEAR, 2019); // 2019年
        cal.set(Calendar.WEEK_OF_YEAR, 54); // 设置为2019年的第34周
        cal.set(Calendar.DAY_OF_WEEK, 1); // 1表示周日，2表示周一，7表示周六
        Date date01 = cal.getTime();

        System.out.println("周一：===：" + df.format(date));
        System.out.println("周日：===：" + df.format(date01));
    }

}
