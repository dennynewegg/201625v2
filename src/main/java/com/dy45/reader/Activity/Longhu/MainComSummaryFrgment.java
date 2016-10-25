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
import android.widget.Button;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Article.Chart2Activity;
import com.dy45.reader.Control.HVListView;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.LonghuDTO;

import java.util.Date;

public class MainComSummaryFrgment extends Fragment {

    // TODO: Rename and change types of parameters

    private HVListView mListView;
    private MainComAdapter adapter ;
    private SwipeRefreshLayout refreshLayout;

    private TextView fromdateView;
    private View fromdateButton;

    private TextView todateView;
    private View todateButton;
    private Button refreshButton;


    public static MainComSummaryFrgment newInstance() {
        MainComSummaryFrgment fragment = new MainComSummaryFrgment();
        return fragment;
    }

    public MainComSummaryFrgment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adapter = new MainComAdapter(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_com_summary, container, false);
        mListView = (HVListView) view.findViewById(R.id.maincom_summary_list);
        mListView.mListHead = (android.widget.LinearLayout) view.findViewById(R.id.mainCom_summary_HV);

        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.maincom_summary_refreshview);
        refreshLayout.setEnabled(false);
        refreshButton = (Button) view.findViewById(R.id.maincom_summary_btn_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainComSummaryFrgment.this.OnRefresh();
            }
        });


        fromdateButton = view.findViewById(R.id.maincom_summary_from_container);
        fromdateView = (TextView) view.findViewById(R.id.maincom_summary_from_date_view);
        fromdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate(fromdateView);
            }
        });

        todateButton = view.findViewById(R.id.maincom_summary_to_container);
        todateView = (TextView) view.findViewById(R.id.maincom_summary_to_date_view);
        todateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate(todateView);
            }
        });
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>-1
                        &&position<adapter.getCount()) {
                    LonghuDTO longhuDTO = (LonghuDTO) adapter.getItem(position);
                    if (longhuDTO != null
                            && !StringUtil.isEmpty(longhuDTO.getCode())) {
                        Intent intent =
                                Chart2Activity.createInstance(MainComSummaryFrgment.this.getActivity(), longhuDTO.getCode());
                        MainComSummaryFrgment.this.getActivity().startActivity(intent);
                    }
                }
            }
        });
        setDate(fromdateView, DateUtil.addDay(new Date(), -2));
        setDate(todateView,new Date());
        return view;
    }

    private void selectDate(final TextView view) {
        DatePickFragment fragment = DatePickFragment.newInstance(
                DateUtil.parseDate(view.getText().toString(), "yyyy-MM-dd")
                ,"day",new OnActionListener1<Date>() {
                    @Override
                    public void onAction(Date date) {
                        setDate(view,date);
                    }
                });
        fragment.setTargetFragment(MainComSummaryFrgment.this,0);
        fragment.show(getActivity().getSupportFragmentManager(),"day");
    }

    private void setDate(TextView view, Date date) {
        view.setText(DateUtil.toDayString(date));
    }

    private void OnRefresh() {
        if(!refreshLayout.isRefreshing()) {

            refreshLayout.setRefreshing(true);
            adapter.refresh(DateUtil.parseDate(fromdateView.getText().toString(), "yyyy-MM-dd")
                    , DateUtil.parseDate(todateView.getText().toString(), "yyyy-MM-dd")
                    , new OnActionListener() {
                        @Override
                        public void onAction() {
                            refreshLayout.setRefreshing(false);
                        }
                    }
            );
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        adapter.clear();
    }

    @Override
    public void onDestroy() {
        adapter.clear();
        super.onDestroy();
    }
}
