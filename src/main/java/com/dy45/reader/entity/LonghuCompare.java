package com.dy45.reader.entity;

import java.util.Comparator;

/**
 * Created by dy45 on 5/23/2015.
 */
public class LonghuCompare implements Comparator<LonghuDTO> {

    @Override
    public int compare(LonghuDTO lhs, LonghuDTO rhs) {
        int value = lhs.getCode().compareTo(rhs.getCode());
        if(value!=0){
            return value;
        }
        value =  lhs.getDate().compareTo(rhs.getDate());
        if(value !=0){
            return value;
        }
        double lnet = lhs.getBuyAmount()-lhs.getSellAmount();
        double rnet = rhs.getBuyAmount()-rhs.getSellAmount();
        return Double.compare(rnet,lnet);
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }
}
