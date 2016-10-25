package com.dy45.reader.sql;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.StockDTO;

import java.io.File;
import java.util.List;

/**
 * Created by dy45 on 10/19/2016.
 */

public class Stockdb {

    static String fileName = "Stock"+ File.separator+"AllStock.txt";

    public static List<StockDTO> getStock(){
        return FileUtil.readArray(fileName,StockDTO.class);
    }

    public static void insertStock(List<DayTradeDTO> dayTradeDTOList){
        List<StockDTO> stockList = ListUtil.select(dayTradeDTOList
                ,dayTradeDTO -> new StockDTO(dayTradeDTO.getCode(),dayTradeDTO.getName()));
        FileUtil.writeObject(fileName,stockList);
    }
}
