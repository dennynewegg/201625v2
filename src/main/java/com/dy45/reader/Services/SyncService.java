package com.dy45.reader.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;

/**
 * Created by dy45 on 5/19/2015.
 */
public class SyncService extends Service {

    public final static String SyncStockParam = "SyncStockParam";
    public final static String SyncLonghuParam = "SyncLonghuParam";
    public final static String SyncDayTradeParam = "SyncDayTradeParam";
    public final static String SyncIpParam = "SyncIpParam";


    public static boolean isRunning = false;
    private boolean isSyncStock = false;
    private boolean isSyncLonghu = false;
    private boolean isSyncDayTrade = false;
    private String serverIp ;

    NotificationManager mNotifyManager;
    NotificationCompat.Builder  mBuilder;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int result = super.onStartCommand(intent, flags, startId);
        readIntent(intent);
        return result;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(android.support.v7.appcompat.R.drawable.abc_list_pressed_holo_light);
//                .setContentIntent(pendingIntent);


        if(!StringUtil.isEmpty(serverIp)){

        }
        else{
            ToastUtil.show(this,"Server ip is empty.");
        }
        stopSelf();
    }

    private void SyncDate(){

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void readIntent(Intent intent){
        isSyncStock = intent.getBooleanExtra(SyncLonghuParam,false);
        isSyncDayTrade = intent.getBooleanExtra(SyncDayTradeParam,false);
        isSyncLonghu = intent.getBooleanExtra(SyncLonghuParam,false);
        serverIp = intent.getStringExtra(SyncIpParam);
    }


    public static Intent createIntent(Context context
            ,boolean isSyncStock
            ,boolean isSyncDayTrade
            ,boolean isSyncLonghu
            ,String serverIp){
        Intent intent = new Intent(context,SyncService.class);
        intent.putExtra(SyncStockParam,isSyncStock);
        intent.putExtra(SyncDayTradeParam,isSyncDayTrade);
        intent.putExtra(SyncLonghuParam,isSyncLonghu);
        intent.putExtra(SyncIpParam,serverIp);
        return intent;
    }
}
