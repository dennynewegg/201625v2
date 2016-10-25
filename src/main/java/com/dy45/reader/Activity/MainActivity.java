package com.dy45.reader.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Article.ArticleActivity;
import com.dy45.reader.Activity.Article.HQActivity;
import com.dy45.reader.Activity.Article.SyncActivity;
import com.dy45.reader.Activity.HQSelector.HQSelectActivity;
import com.dy45.reader.Activity.Longhu.LonghuActivity;
import com.dy45.reader.Activity.Longhu.MainComActivity;
import com.dy45.reader.Util.MathUtil;

public class MainActivity extends Activity {

    private final static String HTSC = "HTSC";
    private final static String ZIXUAN = "ZIXUAN";
    private final static String CIXIN  ="CIXIN";
    private final static String LONGHU="Longhu";
    private final static String Longhu_Summary="Longhu Summary";
    private final static String FastLimitUp = "Fetch Limit Up";
    private final static String SyncDate="Sync";
    private final static String HQSelecor = "HQ";
//    private final static String Test = "Test";
    private TextView memoryUsed;

    private ListView menuList ;
    private String[] menuStrList = new String[]{HTSC,ZIXUAN,CIXIN,LONGHU,Longhu_Summary,SyncDate,HQSelecor};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuStrList);

        menuList = (ListView) findViewById(R.id.main_menu_list);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(position);
            }
        });

        memoryUsed = (TextView) findViewById(R.id.main_MemoryUsed);
        menuList.setAdapter(arrayAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        long totalBytes = Runtime.getRuntime().totalMemory();
        double totalM=totalBytes/1024.0/1024.0;

        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        int maxMemory = activityManager.getMemoryClass();
        memoryUsed.setText(String.valueOf(MathUtil.toDouble2(totalM))
                +"/"
                +MathUtil.toDouble2(maxMemory));
    }

    private void openActivity(int pos) {
        String menuStr = menuStrList[pos];
        Class<?> classzz = null;
        switch (menuStr){
            case HTSC:
                classzz = ArticleActivity.class;
                break;
            case ZIXUAN:
                classzz  = HQActivity.class;
                break;
            case LONGHU:
                classzz = LonghuActivity.class;
                break;
            case Longhu_Summary:
                classzz= MainComActivity.class;
                break;
            case CIXIN:
                break;
            case SyncDate:
               // WYBiz.gethqItem(this);
                classzz = SyncActivity.class;
                break;
            case HQSelecor:
                classzz = HQSelectActivity.class;
        }
        if(classzz!=null) {
            Intent intent = new Intent(this, classzz);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_HTSC) {
//            Intent intent = new Intent(this,ArticleActivity.class);
//            startActivity(intent);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
