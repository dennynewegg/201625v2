package com.dy45.reader.Util;

import java.text.DecimalFormat;

/**
 * Created by dy45 on 4/28/2015.
 */
public class MathUtil {
    public static Double parseDouble(String str){
        return parseDouble(str,0.0);
    }
    public static double parseDouble(String str,double defaultValue){
        try {
            return Double.parseDouble(str);
        }
        catch (Exception ex){
            return defaultValue;
        }
    }

    public static String toString(double data,String format){
        DecimalFormat df = new DecimalFormat(format);//最多保留几位小数，就用几个#，最少位就用0来确定
        return df.format(data);
    }

    public static String toString(double data){
        return toString(data,"0.##");
    }

    public static double toDouble2(double data){
        return parseDouble(toString(data),data);
    }
}
