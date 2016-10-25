package com.dy45.reader.Activity.Longhu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Article.Chart2Activity;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.sql.Longhudb;

import java.util.Date;

public class LonghuListFragment extends Fragment {

    private ListView mListView;
    private TextView dateView;
    private View dateButton;
    private LonghuListAdapter adapter ;
    private SwipeRefreshLayout refreshLayout;
    private Date date;

    public static LonghuListFragment newInstance() {
        LonghuListFragment fragment = new LonghuListFragment();
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        if(adapter.getDate()==null) {
            setDate(dateView, date);
        }
        else {
            setDate(dateView,adapter.getDate());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adapter = new LonghuListAdapter(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = Longhudb.getMaxLonghuDate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_longhu_list, container, false);
        mListView = (ListView) view.findViewById(R.id.longhu_list_view);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.longhu_list_refreshLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LonghuListFragment.this.OnRefresh();
            }
        });


        dateView = (TextView) view.findViewById(R.id.longhu_list_date_view);
        dateButton = view.findViewById(R.id.longhu_list_date_container);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickFragment fragment = DatePickFragment.newInstance(
                        DateUtil.parseDate(dateView.getText().toString(),"yyyy-MM-dd")
                        ,"day",new OnActionListener1<Date>() {
                    @Override
                    public void onAction(Date date) {
                        setDate(dateView,date);
                    }
                });
                fragment.setTargetFragment(LonghuListFragment.this,0);
                fragment.show(getActivity().getSupportFragmentManager(),"day");
            }
        });

        View headerview = getActivity().getLayoutInflater().inflate(R.layout.longhu_list_item,null,false);
        mListView.addHeaderView(headerview);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position-1;
                if(position>-1
                        && position<adapter.getCount()){
                    LonghuDTO longhuDTO = (LonghuDTO) adapter.getItem(position);
                    Intent intent =Chart2Activity.createInstance(LonghuListFragment.this.getActivity(), longhuDTO.getCode());
                    LonghuListFragment.this.startActivity(intent);
                }
            }
        });

        return view;
    }

    public void OnRefresh(){

        Date date = adapter.getDate();
        refreshData(date);

    }

    private void refreshData(final Date date){
        if(date.compareTo(DateUtil.addDay(new Date(), 1))>0){
            refreshLayout.setRefreshing(false);
            return;
        }

        if(!DateUtil.isBizDay(date)){
            refreshData(DateUtil.addDay(date, 1));
            return;
        }

        setDate(dateView,date);
        adapter.refresh(new OnActionListener() {
            @Override
            public void onAction() {
                Date nextDate = DateUtil.addDay(date, 1);
                if(nextDate.compareTo(new Date())<0) {
                    refreshData(nextDate);
                }
            }
        });
    }

    private void setDate(TextView view,Date date){
        view.setText(DateUtil.toString(date,"yyyy-MM-dd"));
        adapter.setDate(date);
    }

}
