package com.abc.case_system.utils;

import org.joda.time.DateTime;

public class TimeInfo {

    public static String time_to_string(String time){
        return time.replace('-', ' ').replace(':', ' ').replace(" ", "");
    }


    public static String get_now_time(){
        return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public static String get_now_time_str(){
        //        DateTime dateTime = new DateTime();
        //        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        return TimeInfo.time_to_string(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }


}
