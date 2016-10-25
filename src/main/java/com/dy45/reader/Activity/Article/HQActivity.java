package com.dy45.reader.Activity.Article;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.BaseActivity;
import com.dy45.reader.StockCodeProider.StockCodeProider;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ProcessUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.LonghuDTO;

import java.util.Date;

public class HQActivity extends BaseActivity {

    ListView listView ;
    SwipeRefreshLayout swipeRefreshLayout;
    HQAdapter adapter;
    int position ;
    boolean isLongClick = false;
    TextView refreshTime;
    RadioButton rbloveCode ;
    RadioButton rbFetchLimitUp;
    Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hq);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.hq_container);
        swipeRefreshLayout.setEnabled(false);
        listView = (ListView) findViewById(R.id.hq_list);
        rbloveCode = (RadioButton)findViewById(R.id.hq_love_stock);
        rbFetchLimitUp = (RadioButton)findViewById(R.id.hq_fetchLimitUp);


//        swipeRefreshLayout.setOnRefreshListener(this);
        View view =  getLayoutInflater().inflate(R.layout.hq_list_item_header,null,false);
        refreshTime = (TextView) findViewById(R.id.hq_refresh_Time);
        listView.addHeaderView(view);
        adapter = new HQAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position1, id) -> {
            position1 = position1 -1;
            if(position1 >=0){
                DayTradeDTO longhuDTO = (DayTradeDTO) adapter.getItem(position1);
                ProcessUtil.openThsApp(HQActivity.this,longhuDTO.getCode(),longhuDTO.getName());
            }
        });


        rbFetchLimitUp.setOnCheckedChangeListener((buttonView, isChecked) ->
                adapter.setHqtype(isChecked?HQAdapter.HQType.FetchLimitUp:HQAdapter.HQType.LoveStock));

        btnRefresh = (Button) findViewById(R.id.btn_hq_refreshhq);
        btnRefresh.setOnClickListener(v -> {
            onRefresh();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_hq, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(position>-1){
            getMenuInflater().inflate(R.menu.menu_hq_context,menu);
            DayTradeDTO dayTradeDTO = (DayTradeDTO) adapter.getItem(this.position);
            menu.setHeaderTitle(dayTradeDTO.getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(StockCodeProider.Proider != null){
            adapter.limitUpProider = StockCodeProider.Proider;
            StockCodeProider.Proider = null;
            rbFetchLimitUp.setChecked(true);
            onRefresh();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if ( id== R.id.action_hq_add) {

            addCode();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addCode() {

        final EditText editText = new EditText(this);


        new AlertDialog.Builder(this)
                .setTitle("Please input")
                .setView(editText)
                .setPositiveButton("OK", (dialog, which) -> {
                    String code = editText.getText().toString();
                    if(!StringUtil.isEmpty(code)){
                        if(StringUtil.isCode(code)){
                            HQActivity.this.adapter.addCode(code);
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancal", (dialog, which) -> dialog.cancel())
                .show();
    }

    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        adapter.Refresh(s -> {
           Date date = adapter.getMaxDate();
           if(date != null) {
               refreshTime.setText(DateUtil.toString(date,"yyyy-MM-dd HH:mm:ss"));
           }
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}
