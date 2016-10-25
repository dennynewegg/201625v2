package com.dy45.reader.Activity.Article;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.SyncBiz;
import com.dy45.reader.File.SharedPreferenceUtil;
import com.dy45.reader.Services.SyncService;
import com.dy45.reader.Util.ExceptionUtil;
import com.dy45.reader.Util.ProgressUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.sql.Longhudb;

public class SyncActivity extends Activity {

    final static String SyncUrlParam = "SyncUrlParam";


    private CheckBox ckbStock;
    private CheckBox ckbDaily;
    private CheckBox ckbLonghu;
    private SwipeRefreshLayout refreshContainer;
    private Button btnRefresh;
    private Button btnDelete;
    private EditText txbIp;
    private ImageButton btnEditIp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        ckbStock = (CheckBox) findViewById(R.id.sync_ckb_stockbase);
        ckbDaily = (CheckBox) findViewById(R.id.sync_ckb_daily);
        ckbLonghu = (CheckBox) findViewById(R.id.sync_ckb_longhu);
        refreshContainer = (SwipeRefreshLayout) findViewById(R.id.sync_refresh_container);

        refreshContainer.setEnabled(false);
        btnRefresh = (Button) findViewById(R.id.sync_btn_start);
        btnRefresh.setOnClickListener(v -> {
//            Intent intent = SyncService.createIntent(this
//                    ,ckbStock.isChecked()
//                    ,ckbDaily.isChecked()
//                    ,ckbLonghu.isChecked()
//                    ,txbIp.getText().toString());
//            startService(intent);
            SyncBiz.syncDayTradeFromWY();
        });


        btnEditIp = (ImageButton) findViewById(R.id.sync_btn_editIp);
        txbIp = (EditText) findViewById(R.id.sync_txb_ip);
        btnEditIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = txbIp.getText().toString();
                if(!StringUtil.isEmpty(ip)){
                    SharedPreferenceUtil.put(SyncActivity.this,SyncUrlParam,ip);
                }
            }
        });
        String ip = SharedPreferenceUtil.get(this, SyncUrlParam);
        if(StringUtil.isEmpty(ip)){
           ip = "192.168.10.100";
        }
        txbIp.setText(ip);

        btnDelete = (Button) findViewById(R.id.sync_btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Longhudb.deleteLonghu();
                        }
                        catch (Exception ex){
                            ExceptionUtil.handle(ex);
                        }
                        return null;
                    }
                };
                task.execute();
                ProgressUtil.showProgress(SyncActivity.this);
            }
        });
    }

}
