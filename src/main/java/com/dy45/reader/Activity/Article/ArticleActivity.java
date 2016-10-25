package com.dy45.reader.Activity.Article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.dy45.reader.Biz.TaogulaBiz;
import com.dy45.reader.entity.ArticleCategory;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.File.SharedPreferenceUtil;
import com.dy45.reader.Biz.HTSCWebBiz;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.ProcessUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.articlereader.R;

public class ArticleActivity extends Activity {

    public final static String SharedPreferenceCookie = "SharedPreferenceCookie";

    private ArticleAdapter madapter;
    private SwipeRefreshLayout swipeRefreshLayout ;
    private ListView articleListView;
    HTSCWebBiz webBiz = new HTSCWebBiz();
    private int RequestLogin_ReasonCode_ArticleList = 1;
    private int RequestLogin_ReasonCode_ArticleDetail = 2;
    private Button btnTaogula ;
    private Button btnHTSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.article_container);
//        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setEnabled(false);
        articleListView = (ListView) findViewById(R.id.article_list);
        madapter = new ArticleAdapter(this);
        articleListView.setAdapter(madapter);
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArticleDTO articleDTO = (ArticleDTO) madapter.getItem(position);
                getArticleDetail(articleDTO);
            }
        });
        btnTaogula = (Button) findViewById(R.id.btnTaogula);
        btnHTSC = (Button)findViewById(R.id.btn_HTSC_article);
        btnTaogula.setOnClickListener(v -> {
            if(!swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(true);
                TaogulaBiz.getArticleList(articleDTOs -> {
                    madapter.setList(articleDTOs);
                    swipeRefreshLayout.setRefreshing(false);
                });
            }
        });
        btnHTSC.setOnClickListener(v->{
            refershHTSCArticleList();
        });
    }

    private void viewFile(ArticleDTO articleDTO){
        if(!ProcessUtil.viewFile(ArticleActivity.this
                , articleDTO.getFileName())){
            ToastUtil.show(ArticleActivity.this,"Open"+articleDTO.getFileName()+"failed.");
        }
    }


    @Override
    protected void onStart() {
        if(madapter.getCount()==0) {
            madapter.read();
        }
        if(StringUtil.isEmpty(webBiz.getCookie())){
            webBiz.setCookie(SharedPreferenceUtil.get(this, SharedPreferenceCookie));
        }

        super.onStart();
    }

    @Override
    protected void onStop() {
        madapter.write();
        madapter.clear();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_hq_add) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            String cookie = data.getStringExtra(HTSCLoginFragment.INTENT_COOKIE);
            if (!StringUtil.isEmpty(cookie)) {
                SharedPreferenceUtil.put(this, SharedPreferenceCookie, cookie);
                webBiz.setCookie(cookie);
                if (requestCode == RequestLogin_ReasonCode_ArticleList) {
                    refershHTSCArticleList();
                }
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private void getArticleDetail(ArticleDTO articleDTO){
        if(StringUtil.isEqual(articleDTO.getCatalog(), ArticleCategory.Taogula)){
            if(!swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(true);
                TaogulaBiz.downloadArticle(articleDTO, articleDTO1 -> {
                    swipeRefreshLayout.setRefreshing(false);
                    if (articleDTO1 != null) {
                        viewFile(articleDTO1);
                    }});
            }
        }
        else {
            getArticleDetailFromHTSC(articleDTO);
        }
    }

    private void getArticleDetailFromHTSC(ArticleDTO articleDTO) {
        if(StringUtil.isEmpty(webBiz.getCookie())){
            Intent intent= new Intent(this,HTSCLoginActivity.class);
            swipeRefreshLayout.setRefreshing(false);
            startActivityForResult(intent,RequestLogin_ReasonCode_ArticleDetail);
        }
        else {
            if(!swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(true);
                webBiz.getArticleDetail(this, articleDTO, articleDTO1 -> {
                swipeRefreshLayout.setRefreshing(false);
                if (articleDTO1 != null) {
                    viewFile(articleDTO1);
                }});
            }
        }
    }

    private void refershHTSCArticleList(){
        if(!swipeRefreshLayout.isRefreshing()) {
            if(StringUtil.isEmpty(webBiz.getCookie())){
                Intent intent= new Intent(this,HTSCLoginActivity.class);
                startActivityForResult(intent,RequestLogin_ReasonCode_ArticleList);
                return;
            }
            swipeRefreshLayout.setRefreshing(true);
            webBiz.getArticleList(this, articleDTOs -> {
                madapter.setList(articleDTOs);
                swipeRefreshLayout.setRefreshing(false);
            });
        }
    }
}
