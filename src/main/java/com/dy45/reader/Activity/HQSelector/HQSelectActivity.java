package com.dy45.reader.Activity.HQSelector;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.app.Fragment;
import com.dy45.articlereader.R;

public class HQSelectActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hqselect);
        Fragment fragment = new hqSelectorFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.hqselect_framelayout,fragment)
                .commit();
    }
}
