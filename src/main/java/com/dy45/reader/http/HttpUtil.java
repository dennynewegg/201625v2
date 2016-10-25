package com.dy45.reader.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpHeaderParser;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by dy45 on 4/22/2015.
 */
public class HttpUtil {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";

    public static String parseResponseCookie(Map<String, String> headers){
        String cookie = headers.get(SET_COOKIE_KEY);
        if(!StringUtil.isEmpty(cookie)) {
            cookie = cookie.replace("path=/,", "");
        }
        return cookie;
    }

    public static String getCharSet(NetworkResponse response,String defautCharSet){
        String charset = defautCharSet;
        if(StringUtil.isEmpty(charset)) {
            charset = HttpHeaderParser.parseCharset(response.headers);
            if (!StringUtil.isEmpty(charset)) {
                charset = charset.replace(",", "");
            }
        }
        return charset;
    }

    public static boolean isGzipEncoding(NetworkResponse response){
        if(response!=null
                && response.headers!=null
                && response.headers.size()>0){
            String contentType = response.headers.get("Content-Encoding");
            if(!StringUtil.isEmpty(contentType)){
                return contentType.contains("gzip");
            }
        }
        return false;
    }


    public static String GetMethodStr(int method){
        if(method == Request.Method.GET){
            return "GET";
        }
        else if(method == Request.Method.POST){
            return "POST";
        }
        else if(method == Request.Method.PUT){
            return "PUT";
        }
        else if(method == Request.Method.DELETE){
            return "DELETE";
        }
        return String.valueOf(method);
    }

}
