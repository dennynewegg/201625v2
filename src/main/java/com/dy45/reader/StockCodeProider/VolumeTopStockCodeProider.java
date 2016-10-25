package com.dy45.reader.StockCodeProider;

import com.dy45.reader.Biz.WYBiz;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.entity.DayTradeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 10/19/2016.
 */

public class VolumeTopStockCodeProider implements IStockCodeProider {

    private int count = 40;
    public VolumeTopStockCodeProider(){

    }

    public VolumeTopStockCodeProider(int count){
        this.count = count;
    }


    @Override
    public void getStockCodeList(final OnActionListener1<List<String>> listener) {
        WYBiz.GetTradeList(listRestResult -> {
            if(listRestResult.hasError()){
                listener.onAction(new ArrayList<>(0));
            }
            else{
                List<DayTradeDTO> list = listRestResult.getResult();
                list = ListUtil.findAll(list,dayTradeDTO ->
                        dayTradeDTO.getRate()>0
                                && dayTradeDTO.getRate()<4);
                if(count< list.size()) {
                    list = list.subList(0, count);
                }
                listener.onAction(ListUtil.select(list
                        , dayTradeDTO -> dayTradeDTO.getCode()));
            }
        }, 100);
    }
}
