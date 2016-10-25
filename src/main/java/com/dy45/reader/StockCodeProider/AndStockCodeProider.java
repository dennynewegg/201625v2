package com.dy45.reader.StockCodeProider;

import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 10/21/2016.
 */

public class AndStockCodeProider implements IStockCodeProider {

    List<IStockCodeProider> mList = new ArrayList<>(10);
    List<List<String>> mStockLists = new ArrayList<>(10);
    private OnActionListener1<List<String>> callBack;

    public void add(IStockCodeProider proider){
        mList.add(proider);
    }


    @Override
    public void getStockCodeList(OnActionListener1<List<String>> listener) {
        callBack = listener;
        getStockCode(0);
    }

    public int size(){
        return mList.size();
    }


    private void getStockList(){
        List<String> stockList = new ArrayList<>(20);
        for (String code : mStockLists.get(0)) {
            boolean noExists = false;
            for (int i=1;i<mStockLists.size();i++){
                if(!ListUtil.exists(mStockLists.get(i),code1->StringUtil.isEqual(code,code1))){
                    noExists = true;
                    break;
                }
            }
            if(!noExists){
                stockList.add(code);
            }
        }
        callBack.onAction(stockList);
    }

    private void getStockCode(final int index){
        if(index >= mList.size()){
            getStockList();
            return;
        }
        mList.get(index).getStockCodeList(list->{
            mStockLists.add(new ArrayList<>());
            mStockLists.get(index).addAll(list);
            int nIndex = index+1;
            getStockCode(nIndex);
        });
    }
}
