package com.example.shareSphere.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo10 {
    public static void main(String[] args) throws ParseException {

        //字符串转换成时间格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a = "2019-03-20 15:15:12";
        Date a1 = sf.parse(a);
        System.out.println("a1===" + a1);

//时间格式转换成字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        Date date1 = new Date();
        String strTiem = simpleDateFormat.format(date1);//时间转换为字符串
        System.out.println("strTiem===========" + strTiem);

//获取两个时间之间的毫秒数
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String b = "2019-03-20 15:15:12";
        String c = "2019-03-21 15:15:13";
        Date b1 = sf1.parse(b);
        Date c1 = sf1.parse(c);

        long diff = (c1.getTime() - b1.getTime()) / 1000;
        System.out.println("diff=======" + diff);

//获取一个时间的毫秒值（将时间转换成毫秒值）
        Date newTime = new Date();
        Long newS = newTime.getTime();//毫秒值
        System.out.println("newTime.getTime()=====" + newS);

//将一个毫秒值转换成时间
        newTime.setTime(newS);//将毫秒值转换设置成时间格式
        System.out.println("newTime=====" + newTime);

    }
}
