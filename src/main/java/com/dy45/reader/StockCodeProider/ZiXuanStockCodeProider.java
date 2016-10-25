package com.dy45.reader.StockCodeProider;

import com.alibaba.fastjson.JSON;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 10/19/2016.
 */

public class ZiXuanStockCodeProider implements  IStockCodeProider {

    @Override
    public void getStockCodeList(OnActionListener1<List<String>> listener) {
        List<DayTradeDTO> list = read();
        listener.onAction(ListUtil.select(list, new OnFunListener1<DayTradeDTO, String>() {
            @Override
            public String Fun(DayTradeDTO dayTradeDTO) {
                return dayTradeDTO.getCode();
            }
        }));
    }

    private final static String HQ_FILE_NAME = "HQ/HQList.txt";
    private final static int MAX_HQ_COUNT = 200;

    public List<DayTradeDTO> read(){
        String articleJson = FileUtil.read(HQ_FILE_NAME);

        if(!StringUtil.isEmpty(articleJson)) {
            return JSON.parseArray(articleJson, DayTradeDTO.class);
        }
        return new ArrayList<DayTradeDTO>(0);
    }

    public void clear(){
        FileUtil.write(HQ_FILE_NAME,"",false);
    }

    public void write(List<DayTradeDTO> list){
        if(!ListUtil.isNullOrEmpty(list)){
            if(list.size()>MAX_HQ_COUNT){
                list = list.subList(0,MAX_HQ_COUNT);
            }
            String json = JSON.toJSONString(list);
            FileUtil.write(HQ_FILE_NAME,json,false);
        }
    }
    public void add(List<String> stockCodes){
        List<DayTradeDTO> list = read();
        for (final String stock : stockCodes) {
            DayTradeDTO daytradeInfo  =new DayTradeDTO();
            if(!ListUtil.exists(list, new OnFunListener1<DayTradeDTO, Boolean>() {
                @Override
                public Boolean Fun(DayTradeDTO dayTradeDTO) {
                    return StringUtil.isEqual(dayTradeDTO.getCode(),stock);
                }
            })) {
                daytradeInfo.setCode(stock);
                list.add(daytradeInfo);
            }
        }
    }
}
