package com.example.shareSphere.date;

import java.text.ParseException;
import java.util.Date;

public class Test1 {

    public static void main(String[] args) throws Exception {
        Date date1 = DateUtil.getDate();
        DateUtil.rechange(5,date1);
    }
}
