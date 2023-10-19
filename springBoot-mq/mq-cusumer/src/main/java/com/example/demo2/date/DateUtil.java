package com.example.shareSphere.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {

    public static  Date getDate()throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        System.out.println("现在时间：" + sdf.format(date));
        return date;
    }
    /**
     * 新曾天数据
     * @param num
     * @throws Exception
     */
    public static String getNowDay(Integer num,Date date)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        System.out.println(sdf.format(calendar.getTime()));
        calendar.add(Calendar.DATE, num);
        System.out.println(sdf.format(calendar.getTime()));
        String now1= sdf.format(calendar.getTime());
        return now1;
    }

    /**
     * 两个日期比较
     * @param dt1
     * @param dt2
     * @return
     * @throws Exception
     */
    public static boolean compare(Date dt1,Date dt2)throws Exception{
        //两个日期比较
        boolean flag = true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         /*dt1 = df.parse("2019-02-03 15:20:56");
        //获取当前时间
         dt2 = new Date();*/
        if (dt1.getTime() > dt2.getTime()) {
            System.out.println(df.format(dt1.getTime()) + "比较大");
        } else if (dt1.getTime() < dt2.getTime()) {
            System.out.println(df.format(dt2.getTime()) + "是当前时间，当前时间比较大");
        }
        if (dt1.getTime() == dt2.getTime()) {
            System.out.println("两个时间一样");
        }
        return flag;
    }
}
