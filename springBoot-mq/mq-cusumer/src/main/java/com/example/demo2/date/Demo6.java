package com.example.shareSphere.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo6 {

    public static void main(String[] args) throws ParseException {

//某个日期之前7天
        int past = -7;
//某个日期之后7天
//int past = 7;

//创建SimpleDateFormat并设置时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = null;
        try {
            //将以上格式的的字符串endTime转换成时间
            dateTime = simpleDateFormat.parse("2019-03-05");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);

        System.out.println("result===" + result);


    }
}
