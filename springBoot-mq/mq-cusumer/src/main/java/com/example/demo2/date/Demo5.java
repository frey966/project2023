package com.example.shareSphere.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 获取两个日期之间相差的月数
 */
public class Demo5 {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = "2020-04-27 12:15:03";
        String str2 = "2018-04-27 01:34:21";
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int surplus = aft.get(Calendar.DATE) - bef.get(Calendar.DATE);
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(surplus);
        surplus = surplus <= 0 ? 1 : 0;
        System.out.println(surplus);
        System.out.println("相差月份：" + (Math.abs(month + result) + surplus - 1));
    }

}
