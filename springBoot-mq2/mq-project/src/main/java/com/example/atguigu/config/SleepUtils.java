package com.example.atguigu.config;

/**
 * 睡眠工具类
 */
public class SleepUtils {

    public static void sleep(int second){

        try {
           Thread.sleep(1000*second);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
