package com.example.shareSphere.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取两个日期之间的相差的天数
 */
public class Demo4 {
    public static void main(String[] args) throws ParseException {
//查询两个之前之间的天数。
//设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//开始时间
        Date startDate = sdf.parse("2019-08-05");
//结束时间
        Date endDate = sdf.parse("2019-08-10");

//得到相差的天数 betweenDate
        long betweenDate = (endDate.getTime() - startDate.getTime()) / (60 * 60 * 24 * 1000);

//打印控制台相差的天数
        System.out.println(sdf.format(startDate) + "与 " + sdf.format(endDate) + "相差" + betweenDate + "天");


    }
}
