package com.dy45.reader.Biz;

import android.content.Context;

import com.dy45.reader.Activity.Chart.ChartDailyFragment;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.http.RequestWrapper;

import java.io.File;

/**
 * Created by dy45 on 5/20/2015.
 */
public class ChartBiz {


    public final static String dailyBaseUrl = "http://image.sinajs.cn/newchart/daily/n/%1s.gif";
    public final static String weeklyBaseUrl = "http://image.sinajs.cn/newchart/weekly/n/%1s.gif";
    public final static String monthlyBaseUrl ="http://image.sinajs.cn/newchart/monthly/n/%1s.gif";
    public final static String minBaseUrl ="http://image.sinajs.cn/newchart/min/n/%1s.gif";


    public final static String ChartBaseUrl="Chart"
            + File.separator;



    public static void getChartImg(Context context,String code,ChartDailyFragment.CodeChartType chartType,OnActionListener1<byte[]> listener1){
        byte[] bytes  = readChart(code, chartType);
        if(bytes!=null
                && bytes.length>0){
            listener1.onAction(bytes);
            return;
        }
        getChartFromWeb(context,code,chartType,listener1);
    }

    private static void getChartFromWeb(Context context, final String code, final ChartDailyFragment.CodeChartType chartType, final OnActionListener1<byte[]> listener1){
        final RequestWrapper wrapper = new RequestWrapper();
        String url = getChartUrl(code,chartType);
        wrapper.getBytes(context,url,new OnActionListener1<RestResult<byte[]>>() {
            @Override
            public void onAction(RestResult<byte[]> restResult) {
                if(!restResult.hasError()){
                    writeChart(code,chartType,restResult.getResult());
                    listener1.onAction(restResult.getResult());
                }
                else {
                    listener1.onAction(null);
                }
            }
        });
    }

    private static void writeChart(String code,ChartDailyFragment.CodeChartType chartType,byte[] bytes){
        String filePath = getChartFilePath(code,chartType);
        FileUtil.write(filePath,bytes,true);
    }

    private static byte[] readChart(String code, ChartDailyFragment.CodeChartType chartType){
        String filePath = getChartFilePath(code,chartType);
        return FileUtil.readBytes(filePath);
    }

    private static String getChartUrl(String code,ChartDailyFragment.CodeChartType chartType){
        String baseUrl = dailyBaseUrl;
        if(chartType== ChartDailyFragment.CodeChartType.Min){
            baseUrl = minBaseUrl;
        }
        else if(chartType==ChartDailyFragment.CodeChartType.Daily){
            baseUrl = dailyBaseUrl;
        }
        else if(chartType == ChartDailyFragment.CodeChartType.Weekly){
            baseUrl = weeklyBaseUrl;
        }
        else if(chartType == ChartDailyFragment.CodeChartType.Month){
            baseUrl = monthlyBaseUrl;
        }
        return String.format(baseUrl,StringUtil.getLongCode(code).toLowerCase());
    }

    private static String getChartFilePath(String code,ChartDailyFragment.CodeChartType chartType){
        String chartTypeStr = chartType.toString();
        if(chartType == ChartDailyFragment.CodeChartType.Min){
            chartTypeStr =chartTypeStr+"_"+ DateUtil.toString("HHmm").substring(0,3);
        }
        return ChartBaseUrl
                + DateUtil.toDayString(DateUtil.getLastDate())
                +File.separator+String.format("%1s_%2s", StringUtil.getShortCode(code),chartTypeStr);
    }
}
