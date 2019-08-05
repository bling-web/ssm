package com.cc.work3.Utils;

import sun.text.resources.FormatData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parseDate(String date){
       return parseDate(date,"yyyy-MM-dd");
    }
    public static Date parseDate(String date,String pattern){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String FormatDate(Date date,String pattern) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    public static String FormatDate(Date date) {
      return FormatDate(date,"yyyy-MM-dd");
    }
}
