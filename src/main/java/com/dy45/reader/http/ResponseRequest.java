package com.dy45.reader.http;

import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.ReaderApp;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.RestResult;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dy45 on 4/22/2015.
 */
public class ResponseRequest extends Request<RestResult<NetworkResponse>> {

    protected static final String CHARSET = "utf-8";
    private Object request;
    private Map<String,String> headers =  new HashMap<String,String>();

    public OnActionListener1<RestResult<NetworkResponse>> getOnFinishedListener() {
        return onFinishedListener;
    }

    public void setOnFinishedListener(OnActionListener1<RestResult<NetworkResponse>> onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

    private OnActionListener1<RestResult<NetworkResponse>> onFinishedListener;

    public ResponseRequest(int method
            , String url
            , Object req
            ,final OnActionListener1<RestResult<NetworkResponse>> listener1) {
        super(method, url, volleyError -> {
            String msg = volleyError.getMessage();

            if(StringUtil.isEmpty(msg)){
                msg = volleyError.toString();
            }
            if(Looper.myLooper() == Looper.getMainLooper()){
                ToastUtil.show(ReaderApp.Instance,  msg);
            }
            if(listener1 !=null){
                HandlerUtil.post(() -> listener1.onAction(new RestResult<NetworkResponse>(volleyError)));
            }
        });
        this.request = req;
        this.onFinishedListener = listener1;
        headers.put("User-Agent","Mozilla/5.0 Chrome/42.0.2311.90");
        headers.put("Accept-Encoding","gzip");
    }

    public void addHeader(String key,String value){
        if(!StringUtil.isEmpty(key)) {
            headers.put(key, value);
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (request == null) {
            return null;
        }
        if(getMethod() == Method.POST
                || getMethod() == Method.PUT) {
            String jsonString;
            try {
                if(request instanceof String){
                    jsonString = request.toString();
                }
                else if(request instanceof Byte[]){
                    return ((byte[])(request));
                }
                else {
                    jsonString = JSON.toJSONString(request);
                }
                return jsonString == null ? null : jsonString.getBytes(CHARSET);
            } catch (UnsupportedEncodingException uee) {
                return null;
            }
        }
        return null;
    }

    @Override
    protected Response<RestResult<NetworkResponse>> parseNetworkResponse(NetworkResponse response) {
        RestResult<NetworkResponse> restResult = new RestResult<NetworkResponse>(response);
        restResult.setCookie(HttpUtil.parseResponseCookie(response.headers));
        return Response.success(restResult,null);
    }

    @Override
    protected void deliverResponse(RestResult<NetworkResponse> networkResponseRestResult) {
//        LogUtil.trace("request http finish " + getMethod()+" :"+getUrl());
//        traceResponse(networkResponseRestResult);

        onResponseListner(networkResponseRestResult);
    }




    private void traceResponse(RestResult<NetworkResponse> networkResponseRestResult) {
        String fileName ="Res"+ File.separator+"Request"+ DateUtil.toString("HH-mm-ss")+"data"+networkResponseRestResult.getResult().statusCode;
        String fileNameHeader ="Res"+ File.separator+"Request"+ DateUtil.toString("HH-mm-ss")+"header";
        FileUtil.write(fileName, networkResponseRestResult.getResult().data, false);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String,String> item:networkResponseRestResult.getResult().headers.entrySet()){
            sb.append(item.getKey()+":"+item.getValue());
        }
        FileUtil.write(fileNameHeader,sb.toString(),false);
    }

    private void onResponseListner(final RestResult<NetworkResponse> restResult){
        if(onFinishedListener !=null){
            HandlerUtil.post(() -> onFinishedListener.onAction(restResult));
        }
    }

    public void addCookie(String cookie){
        if(!StringUtil.isEmpty(cookie)) {
            headers.put("Cookie", cookie);
        }
    }
}
