package com.dy45.reader.entity;

import java.io.Serializable;

/**
 * Created by dy45 on 4/28/2015.
 */
public class RestResult<T> implements Serializable {

    private String cookie ;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public RestResult(T result) {
        this.result = result;
    }

    public Exception getError() {
        return error;
    }

    public RestResult(Exception error) {
        this.error = error;
    }

    private Exception error;
    private T result;

    public boolean hasError(){
        return error!=null;
    }

    public T getResult() {
        return result;
    }
}
