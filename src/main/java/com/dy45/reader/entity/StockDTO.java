package com.dy45.reader.entity;

/**
 * Created by dy45 on 10/19/2016.
 */

public class StockDTO {

    private String code;
    private String name;

    public StockDTO(){

    }

    public StockDTO(String code,String name){
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
