package com.dy45.reader.entity;

import com.dy45.reader.Util.MathUtil;
import com.dy45.reader.Util.StringUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dy45 on 4/28/2015.
 */
public class DayTradeDTO extends StockDTO implements Serializable {

    private double close;
    private double open;
    private double high;
    private double low;
    private double yClose;
    private double avgCost;
    private double volume;
    private Date date;
    private double amount;
    private Double rate;
    private int rowid;

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }




    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getyClose() {
        return yClose;
    }

    public void setyClose(double yClose) {
        this.yClose = yClose;
    }

    public String getRateString() {
        Double rate=getRate();
        if(rate!=null){
            return MathUtil.toString(rate)+"%";
        }
        return "";
    }

    public Double getRate(){
        if(rate!=null){
            return rate;
        }
        if(yClose>0
             && close>0){
            return new Double(MathUtil.toString(100 * (close - yClose) / yClose));
        }
        return  null;
    }

    public void setRate(Double rate){
        this.rate = rate;
    }

    public double getAvgCost(){
        if(avgCost>0){
            return avgCost;
        }
        if(amount>0
                && volume >0){
            return MathUtil.toDouble2(amount/ volume);
        }
        if(close>0
                &&open>0
                && low>0
                && high>0){
            return MathUtil.toDouble2((3*close+open+low+high)/6);
        }
        if(close>0){
            return close;
        }
        if(open>0){
            return open;
        }
        if(yClose>0){
            return yClose;
        }
        return 1;
    }

    public void setAvgCost(double cost){
        if(cost>0) {
            avgCost = cost;
        }
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double qty) {
        this.volume = qty;
    }


    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public boolean isSameCode(DayTradeDTO dayTradeDTO){
        if(dayTradeDTO==null){
            return false;
        }
        if(this == dayTradeDTO){
            return  true;
        }
        return StringUtil.isEqual(StringUtil.getShortCode(this.getCode())
                ,StringUtil.getShortCode(dayTradeDTO.getCode()));
    }
}
