package com.dy45.reader.Biz;

import android.content.Context;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.http.RequestWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/26/2015.
 */
public class HQYahooBiz {

//    http://ichart.yahoo.com/table.csv?s=<string>&a=<int>&b=<int>&c=<int>&d=<int>&e=<int>&f=<int>&g=d&ignore=.csv
//    参数
//    s – 股票名称
//    a – 起始时间，月
//    b – 起始时间，日
//    c – 起始时间，年
//    d – 结束时间，月
//    e – 结束时间，日
//    f – 结束时间，年
//    g – 时间周期。Example: g=w, 表示周期是’周’。d->’日’(day), w->’周’(week)，m->’月’(mouth)，v->’dividends only’
//    一定注意月份参数，其值比真实数据-1。如需要9月数据，则写为08。
    public final static String baseUrl = "http://table.finance.yahoo.com/table.csv?&ignore=.csv&g=d";

    public static void getHQ(Context context, final String code,Date date, final OnActionListener1<List<DayTradeDTO>> listener1){
        RequestWrapper wrapper = new RequestWrapper();
        wrapper.getString(getUrl(code,date,DateUtil.addDay(date,1)),new OnActionListener1<RestResult<String>>() {
            @Override
            public void onAction(RestResult<String> stringRestResult) {
                if(!stringRestResult.hasError()){
                    String csv = stringRestResult.getResult();
                    String[] lines = csv.split("\n");
                    List<DayTradeDTO> list=new ArrayList<DayTradeDTO>();
                    if(lines!=null
                       && lines.length>3) {
                        for (int index = 2; index < lines.length; index++) {
                            String[] columns = lines[index].split(",");
                            if(columns!=null
                               && columns.length>5){
                                DayTradeDTO dayTradeDTO = new DayTradeDTO();
                                dayTradeDTO.setDate(DateUtil.parseDayDate(columns[0]));
                                dayTradeDTO.setOpen(Double.parseDouble(columns[1]));
                                dayTradeDTO.setHigh(Double.parseDouble(columns[2]));
                                dayTradeDTO.setLow(Double.parseDouble(columns[3]));
                                dayTradeDTO.setClose(Double.parseDouble(columns[4]));
                                dayTradeDTO.setVolume(Double.parseDouble(columns[5]));
                                dayTradeDTO.setCode(StringUtil.getLongCode(code));
                                list.add(dayTradeDTO);
                            }
                        }
                    }
                    listener1.onAction(list);
                    return;
                }
                listener1.onAction(null);
            }
        });
    }

    private static String getUrl(String code,Date fromDate,Date toDate){
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(baseUrl);
        urlBuffer.append("s="+getCode(code));
        if(DateUtil.isDate(fromDate)){
            urlBuffer.append("&a="+(DateUtil.getMonth(fromDate)-1));
            urlBuffer.append("&b="+DateUtil.getDayofMonth(fromDate));
            urlBuffer.append("&c="+DateUtil.getYear(fromDate));
        }
        else if(DateUtil.isDate(toDate)){
            urlBuffer.append("&d="+(DateUtil.getMonth(toDate)-1));
            urlBuffer.append("&e="+DateUtil.getDayofMonth(toDate));
            urlBuffer.append("&f="+DateUtil.getYear(toDate));
        }
        return urlBuffer.toString();
    }

    private static String getCode(String code){
        code = StringUtil.getLongCode(code);
        code = code.toUpperCase();

        if(code.startsWith("SH"))
        {
            return code.toLowerCase()+".ss";
        }
        return code.toLowerCase()+".sz";
    }

}
