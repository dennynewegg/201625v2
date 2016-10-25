package com.dy45.reader.Activity.Longhu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.BaseActivity;

public class MainComActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_com);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.maincom_activity_container,new MainComSummaryFrgment())
                .commit();
    }
}
