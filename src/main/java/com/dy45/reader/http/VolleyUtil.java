package com.dy45.reader.http;
import android.content.Context;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.security.AuthProvider;

public class VolleyUtil {

    private static RequestQueue mRequestQueue;

    public static void initVolley(Context context){
//        HurlStack stack = new ProxiedHurlStack();
        mRequestQueue = Volley.newRequestQueue(context,null);
    }

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static <T> Request addToRequestQueue(Request<T> req) {
//        req.setTag(context.getClass().getName());
        return getRequestQueue().add(req);
    }

//    public static class ProxiedHurlStack extends HurlStack {
//
//
////        public ProxiedHurlStack(HttpClient client) {
////            super(client);
////        }
//
//
//
//    }
//
//    static class ProxyAuthenticator extends Authenticator {
//
//        private String user, password;
//
//        public ProxyAuthenticator(String user, String password) {
//            this.user = user;
//            this.password = password;
//        }
//
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(user, password.toCharArray());
//        }
//    }


}
