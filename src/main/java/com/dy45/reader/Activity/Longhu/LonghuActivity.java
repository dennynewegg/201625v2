package com.dy45.reader.Activity.Longhu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.dy45.articlereader.R;
import com.dy45.reader.Activity.BaseActivity;
import com.dy45.reader.Activity.Chart.LonghuDetailFragment;
import com.dy45.reader.http.VolleyUtil;
import com.viewpagerindicator.TabPageIndicator;

public class LonghuActivity extends BaseActivity {

    private ViewPager viewPager ;
    private TabPageIndicator tabPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longhu);
        viewPager = (ViewPager) findViewById(R.id.longhu_list_viewPager);
        viewPager.setAdapter(new LonghuPagerAdpter(getSupportFragmentManager()));

        tabPageIndicator = (TabPageIndicator) findViewById(R.id.longhu_list_tagpager);
        tabPageIndicator.setViewPager(viewPager);
    }

    class LonghuPagerAdpter extends FragmentPagerAdapter{

        private Fragment fragment1 = LonghuListFragment.newInstance();
        private Fragment fragment2 = new LonghuDetailFragment();

        public LonghuPagerAdpter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            if(i==1){
                return fragment1;
            }
            return fragment2;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position ==1) {
                return "longhu";
            }
            else{
                return "detail";
            }
        }
    }

}
