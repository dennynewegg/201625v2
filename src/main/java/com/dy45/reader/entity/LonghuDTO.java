package com.dy45.reader.entity;

import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.StringUtil;

import java.io.Serializable;

/**
 * Created by dy45 on 5/8/2015.
 */
public class LonghuDTO extends DayTradeDTO implements Serializable {

    private String type;
    private String comName;
    private double buyAmount;
    private double sellAmount;
    private double netAmount;

    public String getSYMBOL() {
        return getCode();
    }

    public void setSYMBOL(String SYMBOL) {
        this.setCode(SYMBOL);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public double getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(double buyAmount) {
        this.buyAmount = buyAmount;
    }

    public double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public boolean isEqual(LonghuDTO longhuDTO){
        return isSameCode(longhuDTO)
                && StringUtil.isEqual(getComName(),longhuDTO.getComName())
                && DateUtil.isEqual(getDate(),longhuDTO.getDate())
                && getBuyAmount()==longhuDTO.getBuyAmount()
                && getSellAmount() == longhuDTO.getSellAmount();
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if(this == o){
            return true;
        }
        if(o instanceof LonghuDTO){
            return isEqual((LonghuDTO)o);
        }
        return super.equals(o);
    }
}





