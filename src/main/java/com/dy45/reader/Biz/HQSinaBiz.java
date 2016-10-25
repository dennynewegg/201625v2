package com.dy45.reader.Biz;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.Util.MathUtil;
import com.dy45.reader.http.RequestWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy45 on 4/28/2015.
 */
public class HQSinaBiz {
//    http://hq.sinajs.cn/list=sh601006,sz300010
//var hq_str_sh601006="大秦铁路,14.30,14.32,14.11,14.72,13.87,14.10,14.11,303511334,4341087374,81859,14.10,89700,14.09,148000,14.08,28997,14.07,74600,14.06,122800,14.11,125965,14.12,245012,14.13,78396,14.14,87366,14.15,2015-04-28,15:04:05,00";
//var hq_str_sz300010="立思辰,53.180,54.200,50.890,55.370,49.310,50.880,50.890,19755359,1023670904.150,10130,50.880,4800,50.870,1300,50.860,2000,50.850,200,50.830,52399,50.890,58207,50.900,16200,50.910,900,50.930,1600,50.940,2015-04-28,15:34:34,00";
//    这个字符串由许多数据拼接在一起，不同含义的数据用逗号隔开了，按照程序员的思路，顺序号从0开始。
//            0：”大秦铁路”，股票名字；
//            1：”27.55″，今日开盘价；
//            2：”27.25″，昨日收盘价；
//            3：”26.91″，当前价格；
//            4：”27.55″，今日最高价；
//            5：”26.20″，今日最低价；
//            6：”26.91″，竞买价，即“买一”报价；
//            7：”26.92″，竞卖价，即“卖一”报价；
//            8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
//            9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
//            10：”4695″，“买一”申请4695股，即47手；
//            11：”26.91″，“买一”报价；
//            12：”57590″，“买二”
//            13：”26.90″，“买二”
//            14：”14700″，“买三”
//            15：”26.89″，“买三”
//            16：”14300″，“买四”
//            17：”26.88″
//    (22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
//            30：”2008-01-11″，日期；
//            31：”15:05:32″，时间；

    public final static String SinaHQBaseUrl = "http://hq.sinajs.cn/list=";
    public static Pattern pattern = Pattern.compile("hq_str_([A-Za-z0-9]{8})[^\\\"]+\\\"([^\\\"]+)");


    public static void getSinaHQ(List<String> codeList, OnActionListener1<RestResult<List<DayTradeDTO>>> listener){
        int index = 0;
        StringBuffer stringBuffer = new StringBuffer( 200 );
        while (index<codeList.size()){
            stringBuffer.append(StringUtil.getLongCode(codeList.get(index)));
            stringBuffer.append(",");
            index++;
        }
        getSinaHQStr(stringBuffer.toString(),listener);
    }

//    public static void getSinaHQ(Context context,String code, final OnActionListener1<DayTradeDTO> listener1){
//        List<String> codeList = new ArrayList<>();
//        codeList.add(code);
//        getSinaHQ(context,codeList,new OnActionListener1<RestResult<List<DayTradeDTO>>>() {
//            @Override
//            public void onAction(RestResult<List<DayTradeDTO>> listRestResult) {
//                if(!listRestResult.hasError()
//                        && !ListUtil.isNullOrEmpty(listRestResult.getResult())){
//                    listener1.onAction(listRestResult.getResult().get(0));
//                }
//                else {
//                    listener1.onAction(null);
//                }
//            }
//        });
//    }


    private static void getSinaHQStr(String codeStr, final OnActionListener1<RestResult<List<DayTradeDTO>>> listener){
        if(StringUtil.isEmpty(codeStr)){
            listener.onAction(new RestResult<>(new ArrayList<>()));
            return;
        }
        String url = SinaHQBaseUrl+codeStr.toLowerCase();
        new RequestWrapper().getString(url, stringRestResult -> {
            if(stringRestResult.hasError()){
                listener.onAction(new RestResult<List<DayTradeDTO>>(stringRestResult.getError()));
                return;
            }
            //FileUtil.write("HQString"+DateUtil.toString("HH-mm-ss"),stringRestResult.getResult(),true);
            List<DayTradeDTO> dayTradeDTOs = parseSinaHQList(stringRestResult.getResult());
            listener.onAction(new RestResult<>(dayTradeDTOs));
        });
    }

    private static List<DayTradeDTO> parseSinaHQList(String hqStr){
        if(hqStr.isEmpty()){
            return new ArrayList<DayTradeDTO>();
        }
//        FileUtil.write("HQString",hqStr,false);
        List<DayTradeDTO> list = new ArrayList<DayTradeDTO>(50);
        Matcher matcher = pattern.matcher(hqStr);
        while (matcher.find()){
            DayTradeDTO dayTradeDTO = new DayTradeDTO();
            dayTradeDTO.setCode(matcher.group(1));
            String[] data = matcher.group(2).split(",");
            if(data!=null
                    && data.length>31){
                dayTradeDTO.setName(data[0]);
                dayTradeDTO.setClose(MathUtil.parseDouble(data[3]));
                dayTradeDTO.setOpen(MathUtil.parseDouble(data[1]));
                dayTradeDTO.setHigh(MathUtil.parseDouble(data[4]));
                dayTradeDTO.setLow(MathUtil.parseDouble(data[5]));
                dayTradeDTO.setyClose(MathUtil.parseDouble(data[2]));
                dayTradeDTO.setVolume(MathUtil.parseDouble(data[8]));
                dayTradeDTO.setAmount(MathUtil.parseDouble(data[9]));
                dayTradeDTO.setDate(DateUtil.parseDate(data[30]+" "+data[31],"yyyy-MM-dd HH:mm:ss"));
                if(dayTradeDTO.getClose()==0
                    || dayTradeDTO.getOpen()==0
                    || dayTradeDTO.getDate()==DateUtil.MinDate){
                    continue;
                }
                if(dayTradeDTO.getyClose() == 0){
                    dayTradeDTO.setyClose(dayTradeDTO.getOpen());
                }
                list.add(dayTradeDTO);
            }
        }
        return list;
    }

    public static <T extends DayTradeDTO> void  fillCurrentTrade(final List<T> list
            , int index
            , final OnActionListener listener){
        if(ListUtil.isNullOrEmpty(list)
                || index>=list.size()){
            listener.onAction();
            return;
        }
        StringBuffer stringBuffer = new StringBuffer( 200 );
        for(int loop=0;loop<30;loop++){
            if(index>=list.size()){
                break;
            }
            stringBuffer.append(StringUtil.getLongCode(list.get(index).getCode()));
            stringBuffer.append(",");
            index++;
        }
        if(stringBuffer.length()>0) {
            final int nextIndex = index;
            getSinaHQStr(stringBuffer.toString(),new OnActionListener1<RestResult<List<DayTradeDTO>>>() {
                @Override
                public void onAction(RestResult<List<DayTradeDTO>> listRestResult) {
                    if(!listRestResult.hasError()){
                        if(!ListUtil.isNullOrEmpty(listRestResult.getResult())){
                            for (DayTradeDTO hq:listRestResult.getResult()){
                                String shortCode = StringUtil.getShortCode(hq.getCode());
                                for(DayTradeDTO desc:list){
                                    if(StringUtil.isEqual(StringUtil.getShortCode(desc.getCode()),shortCode)){
                                        copyTo(hq,desc);
                                    }
                                }
                            }
                        }
                    }
                    fillCurrentTrade(list,nextIndex,listener);
                }
            } );
        }
        else{
            listener.onAction();
        }
    }

    public static <T extends DayTradeDTO> void copyTo(T source,T desc){
        if(source == null
                || desc==null) {
            return;
        }
        desc.setCode(source.getCode());
        desc.setName(source.getName());
        desc.setClose(source.getClose());
        desc.setyClose(source.getyClose());
        desc.setHigh(source.getHigh());
        desc.setLow(source.getLow());
        desc.setRate(source.getRate());
        desc.setVolume(source.getVolume());
        desc.setAmount(source.getAmount());
        desc.setOpen(source.getOpen());
    }

}
