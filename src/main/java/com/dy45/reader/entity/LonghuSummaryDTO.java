package com.dy45.reader.entity;

/**
 * Created by user on 2015/5/18.
 */
public class LonghuSummaryDTO extends LonghuDTO {
    private double netQty;
    private int buyCount;
    private double gain;

    public int getBuyCount() {
        return buyCount;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public double getNetQty() {
        return netQty;
    }

    public void setNetQty(double netQty) {
        this.netQty = netQty;
    }
}
