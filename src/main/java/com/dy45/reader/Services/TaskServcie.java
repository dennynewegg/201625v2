package com.dy45.reader.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.TaogulaBiz;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.LogUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.http.RequestWrapper;
import com.dy45.reader.http.ResponseRequest;

import java.util.List;

public class TaskServcie extends Service {
    public TaskServcie() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        String html = get

        LogUtil.trace("TaskService"+Thread.currentThread().getId()+"TaskServcie.onCreate");

        HandlerUtil.sync(() -> {
//                TaogulaBiz.getArticleList(articleDTOs -> {
//                    if(articleDTOs.size()>0) {
//                        FileUtil.writeObject("Taogula", articleDTOs);
//                    }
//                });





        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
