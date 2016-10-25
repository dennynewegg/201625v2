package com.dy45.reader.Util;

import android.util.Log;

import com.dy45.reader.File.FileUtil;
import com.dy45.articlereader.BuildConfig;

import java.util.Date;

/**
 * Created by dy45 on 4/22/2015.
 */
public class LogUtil {

    private final static String LOG_TAG = "";

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(LOG_TAG, msg);
        }
    }

    public static void e(String msg) {
        e(LOG_TAG, msg);
    }

    public static void e(String msg, Throwable throwable) {
        e(LOG_TAG, msg, throwable);
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }else{
            fileWrite("Information "+tag,msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
        fileWrite("Exception "+tag,msg);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, throwable);
        }
        fileWrite("Exception "+tag,msg);
    }

    public static void trace(String msg){
        try{
            if(!StringUtil.isEmpty(msg)) {
                String fileName = String.format("Trace/%1s", DateUtil.toString(new Date(), "yyyy-MM-dd"));
                msg ="\n\n"+ DateUtil.toString(new Date(),"HH:mm:ss")+ "  \t"+msg;
                FileUtil.write(fileName,msg,true);
            }
        }
        catch (Exception ex){
            e("",ex);
        }
    }


    public static void fileWrite(String tag,String msg){
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append(String.format("\n\n=====%1s %2s=====\n",DateUtil.toString("HH:mm:ss"),tag));
        stringBuffer.append(msg);
        stringBuffer.append("\n");
        String fileName = String.format("Exception/%1s", DateUtil.toString(new Date(), "yyyy-MM-dd"));
        FileUtil.write(fileName,stringBuffer.toString(),true);
    }
}
