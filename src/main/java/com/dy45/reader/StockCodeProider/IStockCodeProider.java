package com.dy45.reader.StockCodeProider;

import com.dy45.reader.Util.OnActionListener1;

import java.util.List;

/**
 * Created by dy45 on 10/19/2016.
 */

public interface IStockCodeProider {
    void getStockCodeList(OnActionListener1<List<String>> listener);
}
