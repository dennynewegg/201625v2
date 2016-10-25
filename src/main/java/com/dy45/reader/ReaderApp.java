package com.dy45.reader;

import android.app.Application;
import android.content.Intent;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.TaogulaBiz;
import com.dy45.reader.Services.TaskServcie;
import com.dy45.reader.Util.ExceptionUtil;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.http.VolleyUtil;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.List;

/**
 * Created by dy45 on 4/25/2015.
 */
public class ReaderApp extends Application {

    public static  ReaderApp Instance ;

    @Override
    public void onCreate() {
        super.onCreate();

        Instance = this;
        VolleyUtil.initVolley(this.getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, final Throwable ex) {
                ExceptionUtil.handle(ex);
            }
        });

//        Authenticator authenticator = new Authenticator() {
//            public PasswordAuthentication getPasswordAuthentication() {
//                return (new PasswordAuthentication("xxxx",
//                        "xxxxx".toCharArray()));
//            }
//        };

//        Authenticator.setDefault(authenticator);

        startService(new Intent(this.getApplicationContext(), TaskServcie.class));

    }
}
