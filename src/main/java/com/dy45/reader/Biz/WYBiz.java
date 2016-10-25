package com.dy45.reader.Biz;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dy45.articlereader.R;
import com.dy45.reader.Util.ExceptionUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.entity.WYHQDTO;
import com.dy45.reader.entity.WYHQDTOItem;
import com.dy45.reader.http.RequestWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 9/10/2016.
 */
public class WYBiz {

    private static String hq163url =
            "http://quotes.money.163.com/hs/service/diyrank.php?page=0&query=STYPE:EQA&sort=VOLUME&order=desc&count=%d&type=query";


    public static void GetTradeList(final OnActionListener1<RestResult<List<DayTradeDTO>>> listener,int count){
        String url = String.format(hq163url,count);
        new RequestWrapper().getString(url,new OnActionListener1<RestResult<String>>() {
            @Override
            public void onAction(RestResult<String> stringRestResult) {
                if(stringRestResult.hasError()){
                    listener.onAction(new RestResult<List<DayTradeDTO>>(stringRestResult.getError()));
                    return;
                }
                String json = stringRestResult.getResult();
                WYHQDTO hqr=null;
                try {
                    hqr = JSON.parseObject(json, WYHQDTO.class);
                }
                catch (Exception ex){
                    LogUtil.trace(json);
                    ExceptionUtil.handle(ex);
                    listener.onAction(new RestResult<List<DayTradeDTO>>(ex));
                }
                List<DayTradeDTO> dayTradeDTOs = new ArrayList<DayTradeDTO>(100);
                if(hqr != null
                        && hqr.getList()!= null
                        && hqr.getList().size()>0)
                {
                    for (int i = 0;i<hqr.getList().size();i++){
                        WYHQDTOItem hitem = hqr.getList().get(i);
                        DayTradeDTO dt=new DayTradeDTO();
                        dt.setCode(hitem.getSYMBOL());
                        dt.setName(hitem.getSNAME());
                        dt.setVolume(hitem.getVOLUME());
                        dt.setLow(hitem.getLOW());
                        dt.setHigh(hitem.getHIGH());
                        dt.setAmount(hitem.getTURNOVER());
                        dt.setOpen(hitem.getOPEN());
                        dt.setClose(hitem.getPRICE());
                        dt.setRate(hitem.getPERCENT()*100);
                        dayTradeDTOs.add(dt);
                    }
                }
                listener.onAction(new RestResult<>(dayTradeDTOs));
            }
        });
    }


//    public static List<WYHQDTOItem> gethqItem(Context context ){
//        String json = context.getResources().getString(R.string.wyHq_body);
//        try{
//            return JSON.parseObject(json,WYHQDTO.class).getList();
//        }
//        catch (Exception ex){
//            ExceptionUtil.handle(ex);
//        }
//        return null;
//    }
}
