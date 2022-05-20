package com.example.myapplication.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String BIRTH_OF_DATE_FORMAT = "dd-MM-yyyy";
    public static final String TIME_POINT_ATTENDANCE = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FOR_LIST_ATTENDANCE = "yyyy-MM-dd HH";

    public static Date toBirthOfDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BIRTH_OF_DATE_FORMAT);
        return simpleDateFormat.parse(date);
    }

    public static Date toDatePointAttendance(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_POINT_ATTENDANCE);
        return simpleDateFormat.parse(date);
    }

    public static Date toDateForListAttendance(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FOR_LIST_ATTENDANCE);
        return simpleDateFormat.parse(date);
    }

    public static String toString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_POINT_ATTENDANCE);
        return simpleDateFormat.format(date);
    }

    public static String toStringBirthOfDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(BIRTH_OF_DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static String timePointToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FOR_LIST_ATTENDANCE);
        return simpleDateFormat.format(date);
    }
}
