package com.dy45.reader.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.Util.StreamUtils;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.RestResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by dy45 on 4/22/2015.
 */
public class RequestWrapper  {
    private Map<String,String> headers =  new HashMap<String,String>();
    private String charSet;

    public RequestWrapper(){
//        headers.put("Proxy-Authorization","Negotiate TlRMTVNTUAADAAAAGAAYAIAAAABAAUABmAAAABAAEABYAAAACAAIAGgAAAAQABAAcAAAABAAEADYAQAAFYKI4goAWikAAAAPzzB3jbvba+4Tl8ESvLB0vEEAQgBTAF8AQwBPAFIAUABkAHkANAA1AFcAQwBNAEkAUwAxADgANgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHz6r63lHfr5YKxaqzdp9+AQEAAAAAAAC9V43bRSzSAcy4TA3h32WPAAAAAAIAEABBAEIAUwBfAEMATwBSAFAAAQASAFMAVAAwADEAVABNAEcAMAA1AAQAFgBiAHUAeQBhAGIAcwAuAGMAbwByAHAAAwAqAFMAVAAwADEAVABNAEcAMAA1AC4AYgB1AHkAYQBiAHMALgBjAG8AcgBwAAUAFgBiAHUAeQBhAGIAcwAuAGMAbwByAHAABwAIAL1XjdtFLNIBBgAEAAIAAAAIADAAMAAAAAAAAAAAAAAAACAAAFyvch4i+NQwyz6NTqfo/9GKlkSmxKgpVKmk1SY/3Gw5CgAQAAAAAAAAAAAAAAAAAAAAAAAJABwASABUAFQAUAAvADEAMgA3AC4AMAAuADAALgAxAAAAAAAAAAAAAAAAAJpdkX3VH+dBT71SpkfQGBY=");
    }


    private ResponseRequest getRequest(String url
            ,int method
            ,Object req
            ,OnActionListener1<RestResult<NetworkResponse>> listener){
        ResponseRequest request =  new ResponseRequest(method,url,req,listener);
        return request;
    }

    public void getString(String url, OnActionListener1<RestResult<String>> listener){

        StringResponse stringResponse = new StringResponse();
        stringResponse.CharSet = this.charSet;

        ResponseRequest request = getRequest(url,Request.Method.GET,null,new TranferListener(listener,stringResponse));
        Log.i("Information","get:"+url);
        addToRequestQueue(request);
    }

    public void getBytes(Context context,String url,OnActionListener1<RestResult<byte[]>> listener){
        ResponseRequest request = getRequest(url,Request.Method.GET,null,new TranferListener(listener,new BytesResponse()));
        Log.i("Information","get:"+url);
        addToRequestQueue(request);
    }

    public void postString(String url, Object req, OnActionListener1<RestResult<String>> listener){
        StringResponse stringResponse = new StringResponse();
        stringResponse.CharSet = this.charSet;
        ResponseRequest request = getRequest(url,Request.Method.POST,req,new TranferListener(listener,stringResponse));
        Log.i("Information","Post:"+ url);
        if(req!=null){
            Log.i("Information","Request"+ JSON.toJSONString(req));
        }
        addToRequestQueue(request);
    }

    private void addToRequestQueue(ResponseRequest req){
        if(headers!=null){
            for(Map.Entry<String,String> item : headers.entrySet()){
                req.addHeader(item.getKey(),item.getValue());
            }
        }
        StringBuffer sb = new StringBuffer(126);
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if(stackTraceElements!=null){
            for(StackTraceElement ste:stackTraceElements){
                sb.append("\t\tat" + ste.toString());
            }
        }
        sb.append(HttpUtil.GetMethodStr(req.getMethod())+":");
        sb.append(req.getUrl());
        LogUtil.trace(sb.toString());
        req.setRetryPolicy(new DefaultRetryPolicy(60*1000,5,1));
        VolleyUtil.addToRequestQueue(req);
    }

    public void addCookie(String cookie){
        if(!StringUtil.isEmpty(cookie)) {
            headers.put("Cookie", cookie);
        }
    }

    public void addHeader(String key,String value){
        if(!StringUtil.isEmpty(key)) {
            headers.put(key, value);
        }
    }

    public void setResponseCharSet(String charSet) {
        this.charSet = charSet;
    }

    private static class StringResponse implements OnFunListener1<NetworkResponse,String>{

        static BytesResponse bytesResponse = new BytesResponse();
        public String CharSet;

        @Override
        public String Fun(NetworkResponse response) {
            CharSet = HttpUtil.getCharSet(response,CharSet);
            byte[] data = bytesResponse.Fun(response);
            try {
                return new String(data,CharSet);
            }
            catch (Exception ex){
                return new String(data);
            }
        }
    }

    private static class BytesResponse implements   OnFunListener1<NetworkResponse,byte[]>{
        @Override
        public byte[] Fun(NetworkResponse response) {
            if(HttpUtil.isGzipEncoding(response)){
                try {
                    GZIPInputStream gStream =
                            new GZIPInputStream( new ByteArrayInputStream( response.data));
                    return StreamUtils.toByteArray(gStream);
                }
                catch (Exception ex){
                }
            }
            return response.data;
        }
    }

    private static class TranferListener<T> implements OnActionListener1<RestResult<NetworkResponse>>    {

        private OnActionListener1<RestResult<T>> listener1 ;
        private OnFunListener1<NetworkResponse,T> funListener1;


        public TranferListener(OnActionListener1<RestResult<T>> listener1,OnFunListener1<NetworkResponse,T> funListener1){
            this.listener1 = listener1;
            this.funListener1 = funListener1;
        }

        @Override
        public void onAction(final RestResult<NetworkResponse> restResult) {
            if(listener1!=null && funListener1!=null){
                if(restResult.hasError()){
                    listener1.onAction(new RestResult<T>(restResult.getError()));
                }
                else{
                    HandlerUtil.post(() -> {
                        RestResult<T> result = new RestResult<T>(funListener1.Fun(restResult.getResult()));
                        result.setCookie(restResult.getCookie());
                        listener1.onAction(result);
                    });
                }
            }
        }
    }
}






