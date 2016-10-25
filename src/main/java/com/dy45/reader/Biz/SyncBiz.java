package com.dy45.reader.Biz;

import android.content.Context;
import android.net.Uri;

import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.sql.DayTradedb;
import com.dy45.reader.sql.Stockdb;

import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/27/2015.
 */
public class SyncBiz {

    private static String buildUrl(String baseUrl,String restName){
        String fullUrl = "";
        if(StringUtil.isEmpty(baseUrl)){
            fullUrl = restName;
        }
        else if(StringUtil.isEmpty(restName)){
            fullUrl = baseUrl;
        }
        else{
            fullUrl = baseUrl+restName;
        }
        if(!StringUtil.isEmpty(fullUrl)){
            fullUrl = fullUrl.toLowerCase();
            if(!fullUrl.startsWith("http://")){
                fullUrl = "http://"+fullUrl;
            }
        }
        return fullUrl;
    }

    public static void syncStock(Context context,String baseUrl,OnActionListener listener){
        String url = buildUrl(baseUrl,"/Sync/stock");


    }

    public static void syncDayTrade(Context context,String baseUrl,Date date,OnActionListener listener){

    }

    public static void syncDayTradeFromWY(){
        WYBiz.GetTradeList(listRestResult -> {
            if(!listRestResult.hasError()
                    && listRestResult.getResult().size()>0){
                DayTradedb.insertDayTrade(listRestResult.getResult());
                Stockdb.insertStock(listRestResult.getResult());
            }
        },3000);
    }

    public static void syncLonghu(Context context,String baseUrl,Date date,OnActionListener listener){

    }
}
