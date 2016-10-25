package com.dy45.reader.entity;

/**
 * Created by dy45 on 10/22/2016.
 */

public class htsctokendto{

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken_key() {
        return token_key;
    }

    public void setToken_key(String token_key) {
        this.token_key = token_key;
    }

    public String getToken_value() {
        return token_value;
    }

    public void setToken_value(String token_value) {
        this.token_value = token_value;
    }

    private String success;
    private String token_key;
    private String token_value;
}