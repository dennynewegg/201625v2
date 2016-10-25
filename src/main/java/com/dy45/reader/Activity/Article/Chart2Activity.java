package com.dy45.reader.Activity.Article;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.BaseActivity;
import com.dy45.reader.Activity.Chart.ChartDailyFragment;
import com.dy45.reader.Activity.Chart.LonghuDetailFragment;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class Chart2Activity extends BaseActivity {

    public final static String Code_Param ="Code";
    public ViewPager viewPager ;
    chartPageAdapter adapter = null;

    public ViewPager detailViewPager = null;
    CodeDetailAdpter detailAdpter = null;

    public static Intent createInstance(Context context, String code){
        Intent intent = new Intent(context, Chart2Activity.class);
        intent.putExtra(Chart2Activity.Code_Param,code);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart2);
        viewPager  = (ViewPager) findViewById(R.id.code_view_viewPager);
        adapter = new chartPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        detailAdpter = new CodeDetailAdpter(getSupportFragmentManager());
        detailViewPager = (ViewPager) findViewById(R.id.code_detail_viewPager);
        detailViewPager.setAdapter(detailAdpter);



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ChartDailyFragment fragment = (ChartDailyFragment) adapter.getFragment(position);
                if(fragment!=null) {
                    Chart2Activity.this.setTitle(fragment.getChartType().toString());
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Intent intent = null;
        try {
            intent = getIntent();
        }
        catch (Exception ex){

        }
        String code = "";
        if(intent != null){
            code = intent.getStringExtra(Code_Param);
        }
        if(StringUtil.isEmpty(code)){
            ToastUtil.show(this, "Please input code.");
        }
        adapter.setCode(code);
        detailAdpter.setCode(code);

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.clear();
    }

    class chartPageAdapter extends FragmentPagerAdapter {

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;

        public void clear(){
            mList.clear();
        }


        List<ChartDailyFragment> mList = new ArrayList<>();

        public chartPageAdapter(FragmentManager fm) {
            super(fm);
            mList.add(new ChartDailyFragment());
            mList.add(new ChartDailyFragment());
            mList.add(new ChartDailyFragment());
            mList.add(new ChartDailyFragment());

            mList.get(0).setChartType(ChartDailyFragment.CodeChartType.Daily);
            mList.get(1).setChartType(ChartDailyFragment.CodeChartType.Min);
            mList.get(2).setChartType(ChartDailyFragment.CodeChartType.Weekly);
            mList.get(3).setChartType(ChartDailyFragment.CodeChartType.Month);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mList.get(position);
            Bundle bundle = new Bundle();
            bundle.putString(ChartDailyFragment.Code_Param,code);
            fragment.setArguments(bundle);
            return fragment;
        }

        public Fragment getFragment(int pos){
            return mList.get(pos);
        }


        @Override
        public int getCount() {
            return mList.size();
        }
    }

    class CodeDetailAdpter extends FragmentPagerAdapter{
        private String code;

        LonghuDetailFragment fragment = new LonghuDetailFragment();

        public CodeDetailAdpter(FragmentManager fm) {
            super(fm);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString(ChartDailyFragment.Code_Param, code);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }


    }

}
