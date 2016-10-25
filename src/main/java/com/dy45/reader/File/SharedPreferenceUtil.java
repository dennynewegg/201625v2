package com.dy45.reader.File;

import android.content.Context;
import android.content.SharedPreferences;

import com.dy45.reader.Util.StringUtil;

/**
 * Created by user on 2015/4/26.
 */
public class SharedPreferenceUtil {
    public static String get(Context context,String name){
        SharedPreferences preferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        String value = preferences.getString(name,null);
        if(value==null){
            return StringUtil.Empty();
        }
        return value;
    }

    public static void put(Context context,String name,String value){
        SharedPreferences preferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name,value).commit();
    }

}
