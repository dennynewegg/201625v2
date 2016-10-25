package com.dy45.reader.entity;

import com.dy45.reader.Util.StringUtil;

/**
 * Created by dy45 on 5/9/2015.
 */
public class LonghuType {

    private final static String OneDayType = "01,04,03,02";

    public static boolean isSameType(String str1,String str2){
        if(OneDayType.contains(str1)&& OneDayType.contains(str2)){
            return true;
        }
        return StringUtil.isEqual(str1,str2);
    }

    public static String GetString(String str){
        switch (str){
            case "01":
                return "涨幅大于7%";
            case "02":
                return "跌幅大于7%";
            case "04":
                return "换手率大于20%";
            case "05":
                return "三日涨幅大于20%";
            case "06":
                return "三日跌幅大于20%";
            default:
                return str;
        }
    }

}
