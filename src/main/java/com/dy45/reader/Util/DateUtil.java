package com.dy45.reader.Util;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dy45 on 4/24/2015.
 */
public class DateUtil {

    public final static Date MinDate= parseDate(1970,1,1);
    public final static Date StartDate = parseDate(2010,1,1);



    public static String toString(Date date, String format){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String toString(String format){
        return toString(new Date(), format);
    }

    public static String toDayString(Date date){
        return toString(date,"yyyy-MM-dd");
    }

    public static String toSqlString(Date date){
        if(date!=null
           &&date.compareTo(StartDate)>0){
            return "'"+DateUtil.toDayString(date)+"'";
        }
        return "''";
    }

    public static boolean isBizDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek>1 && dayOfWeek<7;
    }

    public static boolean isBizTime(Date date){
        if(isBizDay(date)){
            String time = toString(date,"HH:mm");
            if((StringUtil.moreThan(time, "09:25")
                    && StringUtil.lessThan(time,"11:30"))
                    || (StringUtil.moreThan(time, "13:00")
                    && StringUtil.lessThan(time,"15:00"))){
                return true;
            }
        }
        return false;
    }

    public static boolean isBizTime(){
        return isBizTime(new Date());
    }

    public static boolean isBizDay(){
        return isBizDay(new Date());
    }

    public static Date parseDate(String date,String format){
        return parseDate(date,format,MinDate);
    }

    public static Date parseDayDate(String date){
        return parseDate(date,"yyyy-MM-dd");
    }


    public static Date parseDate(int year,int month,int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month,day,0,0,0);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int day){
        return add(Calendar.DATE, date, day);
    }

    private static Date add(int field,Date date,int value){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field,value);
        return calendar.getTime();
    }

    public static boolean isEqual(Date date1,Date date2){
        if(date1==date2){
            return true;
        }
        if(date1==null
                || date2==null){
            return false;
        }
        return toString(date1,"yyyy-MM-dd").compareTo(toString(date2,"yyyy-MM-dd"))==0;
    }

    public static Date addHour(Date date, int hour){
        return add(Calendar.HOUR, date, hour);
    }

    public static Date parseDate(String date,String format,Date defaultValue){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        }
        catch (Exception ex){
            return defaultValue;
        }
    }

    public static int getYear(Date date){
        return Integer.parseInt(toDayString(date).substring(0,4));
    }
    public static int getMonth(Date date){
        return Integer.parseInt(toDayString(date).substring(4,6));
    }
    public static int getDayofMonth(Date date){
        return Integer.parseInt(toDayString(date).substring(6,8));
    }


    public static Date getLastDate(Date date){
        if(date.getHours()<15){
            date = addDay(date,-1);
        }
        while (!isBizDay(date)){
            date = addDay(date,-1);
        }
        return DateUtil.parseDate(DateUtil.toDayString(date),"yyyy-MM-dd");
    }
    public static Date getLastDate() {
        return getLastDate(new Date());
    }

    public static boolean isDate(Date date){
        if(date == null){
            return false;
        }
        return date.compareTo(StartDate)>0;
    }

}
