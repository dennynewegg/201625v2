package com.dy45.reader.Util;


import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by dy45 on 4/24/2015.
 */
public class HandlerUtil {
    public static void post(Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    public static void sync(final OnActionListener listener){
        sync(new OnFunListener<Boolean>() {
            @Override
            public Boolean fun() {
                listener.onAction();
                return true;
            }
        }, new OnActionListener1<Boolean>() {
            @Override
            public void onAction(Boolean aBoolean) {

            }
        });
    }

    public static <Result> void sync(final OnFunListener<Result> listener1
            , final OnActionListener1<Result> callback){
        AsyncTask<Void,Void,Result> task = new AsyncTask<Void, Void, Result>() {
            @Override
            protected Result doInBackground(Void... params) {
                Result result =  null;
                try {
                   result = listener1.fun();
                }
                catch (Exception ex){
                    ExceptionUtil.handle(ex);
                }
                return result;
            }

            @Override
            protected void onPostExecute(Result result) {
                try {
                    if(callback != null) {
                        callback.onAction(result);
                    }
                }
                catch (Exception ex){
                    ExceptionUtil.handle(ex);
                }
            }
        };
        task.execute();
    }
}
