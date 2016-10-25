package com.dy45.reader.Util;

import com.dy45.reader.File.FileUtil;

/**
 * Created by dy45 on 4/22/2015.
 */
public class StringUtil {
    public final static String UTF8="UTF-8";


    public static boolean isEmpty(CharSequence str){
        return str==null || str.length()==0;
    }

    public static String Empty(){
        return "";
    }

    public static String getShortCode(String code){
        if(isEmpty(code)){
            return code;
        }
        code = code.toUpperCase();
        if((code.startsWith("SH")
                || code.startsWith("SZ"))
                && code.length()==8){
            code = code.substring(2,8);
        }
        if(code.length() == 6){
            if(code.startsWith("60")
                    || code.startsWith("30")
                    || code.startsWith("00"))
            {
                return  code;
            }
        }
        return "";
    }

    public static String getLongCode(String code){
        if(isEmpty(code)){
            return code;
        }
        if(code.length()==6){
            if(code.startsWith("6")){
                return "SH"+code;
            }
            return "SZ"+code;
        }
        return code;
    }

    public static boolean isEqual(String str1,String str2){
        if(isEmpty(str1)
             && !isEmpty(str2)){
            return false;
        }
        return str1.equalsIgnoreCase(str2);

    }


    public static boolean moreThan(String str1, String str2){
        if(isEmpty(str1)){
            return false;
        }
        return str1.compareToIgnoreCase(str2)>0;
    }

    public static boolean isCode(String str){
        str = getShortCode(str);
        if(!isEmpty(str)){
            boolean result = (str.length()==6);
            return result;
        }
        return false;
    }


    public static boolean lessThan(String str1, String str2){
        return moreThan(str2, str1);
    }

}
