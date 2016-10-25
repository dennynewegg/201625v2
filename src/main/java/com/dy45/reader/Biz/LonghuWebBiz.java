package com.dy45.reader.Biz;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.http.RequestWrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy45 on 5/8/2015.
 */
public class LonghuWebBiz {

//    http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml?tradedate=2015-05-06
    //http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?symbol=002047&tradedate=2015-05-06&type=02
    final static String LongHuListBaseUrl = "http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml?tradedate=%1s";
    final static String LongHuDetailPrefixUrl = "http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?";
    final static String LongHuDetailBaseUrl ="symbol=%1s&tradedate=%2s&type=";

    private static Pattern longhuListPattern = Pattern.compile("onclick=\\\"showDetail\\('(\\d{2})','(\\d{6})','([\\d-]{10})',this");
    private static Pattern longhuBuySellPattern = Pattern.compile("buy:(\\[[^\\]]+\\])[^,]*,sell:(\\[[^\\]]+\\])");


    public static void getLonghuList(Context context, Date date, final OnActionListener1<List<LonghuDTO>> listener1) {
        if(!DateUtil.isBizDay(date)){
            listener1.onAction(null);
        }

        String url = String.format(LongHuListBaseUrl, DateUtil.toString(date, "yyyy-MM-dd"));
        RequestWrapper wrapper = new RequestWrapper();
        wrapper.getString(url, new OnActionListener1<RestResult<String>>() {
            @Override
            public void onAction(RestResult<String> stringRestResult) {
                if (!stringRestResult.hasError()) {
                    listener1.onAction(parseLonghuList(stringRestResult.getResult()));
                } else {
                    listener1.onAction(null);
                }
            }
        });
    }

    private static List<LonghuDTO> parseLonghuList(String htmlContent){
        List<LonghuDTO> longhuList = new ArrayList<>(50);
        if(!StringUtil.isEmpty(htmlContent)){
            Matcher matcher = longhuListPattern.matcher(htmlContent);
            while (matcher.find()){
                if(StringUtil.isCode(matcher.group(2))) {
                    LonghuDTO longhuDTO = new LonghuDTO();
                    longhuDTO.setType(matcher.group(1));
                    longhuDTO.setCode(matcher.group(2));
                    longhuDTO.setDate(DateUtil.parseDate(matcher.group(3), "yyyy-MM-dd"));
                    longhuList.add(longhuDTO);
                }
            }
        }
        return longhuList;
    }

    public static void getLonghuDetail(Context context, final LonghuDTO longhuDTO,final OnActionListener1<List<LonghuDTO>> listener1){
        if(!StringUtil.isEmpty(longhuDTO.getType())) {
            String url = LongHuDetailPrefixUrl +
                    String.format(LongHuDetailBaseUrl
                            ,StringUtil.getShortCode(longhuDTO.getCode())
                            ,DateUtil.toString(longhuDTO.getDate()
                            , "yyyy-MM-dd"))+longhuDTO.getType().trim();
            RequestWrapper wrapper = new RequestWrapper();
            wrapper.setResponseCharSet("GB2312");
            wrapper.getString(url, new OnActionListener1<RestResult<String>>() {
                @Override
                public void onAction(RestResult<String> stringRestResult) {
                    List<LonghuDTO> detailList = null;
                    if (!stringRestResult.hasError()) {
                        try {
                            detailList = parseLonghuDetail(stringRestResult.getResult(), longhuDTO);
                        } catch (Exception ex) {

                        }
                    }
                    listener1.onAction(detailList);
                }
            });

//            wrapper.getBytes(context,url,new OnActionListener1<RestResult<byte[]>>() {
//                @Override
//                public void onAction(RestResult<byte[]> restResult) {
//                    FileUtil.write("Test/"+longhuDTO.getCode()+"_"+longhuDTO.getType(),restResult.getResult(),false);
//                    listener1.onAction(null);
//                }
//            });

        }
    }

    private static List<LonghuDTO> parseLonghuDetail(String html,LonghuDTO originLongHu){
        List<LonghuDTO> longhuDTOs = new ArrayList<>(10);
        if(!StringUtil.isEmpty(html)){
            Matcher matcher = longhuBuySellPattern.matcher(html);
            while (matcher.find()){
                String buyStr = matcher.group(1);
                String sellStr = matcher.group(2);
                List<LonghuDTO> buyList = JSON.parseArray(buyStr, LonghuDTO.class);
                List<LonghuDTO> sellList = JSON.parseArray(sellStr,LonghuDTO.class);
                if(!ListUtil.isNullOrEmpty(buyList)){
                    longhuDTOs.addAll(buyList);
                }
                if(!ListUtil.isNullOrEmpty(sellList)){
                    longhuDTOs.addAll(sellList);
                }
            }
        }
        if(!ListUtil.isNullOrEmpty(longhuDTOs)){
            for (LonghuDTO item:longhuDTOs){
                item.setDate(originLongHu.getDate());
                item.setCode(StringUtil.getLongCode(originLongHu.getCode()));
                item.setName(originLongHu.getName());
                item.setAvgCost(originLongHu.getAvgCost());
            }
        }
        return longhuDTOs;
    }
}
