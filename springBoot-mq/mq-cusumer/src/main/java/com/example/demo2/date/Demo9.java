package com.example.shareSphere.date;

import java.text.ParseException;
import java.util.Calendar;

public class Demo9 {
    public static void main(String[] args) throws ParseException {
//获取当前时间到今天晚上12点的毫秒数
        long current = System.currentTimeMillis();// 当前时间毫秒数
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowzero = calendar.getTimeInMillis();
        long tomorrowzeroSeconds = (tomorrowzero - current);
        System.out.println("当前时间毫秒数：" + current);
        System.out.println("不知道是啥" + tomorrowzero);
        System.out.println("现在到今晚12点的毫秒数：" + tomorrowzeroSeconds);
    }
}
