package com.dy45.reader.Activity.HQSelector;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Article.HQActivity;
import com.dy45.reader.StockCodeProider.AndStockCodeProider;
import com.dy45.reader.StockCodeProider.ArticleStockCodeProider;
import com.dy45.reader.StockCodeProider.StockCodeProider;
import com.dy45.reader.StockCodeProider.VolumeTopStockCodeProider;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.ArticleDTO;

import java.util.List;


public class hqSelectorFragment extends Fragment {

    ListView lstviewHQ ;
    HQSelectorAdapter adapter;
    Button btnSearch;
    CheckBox ckLimitUp;

    public hqSelectorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_hq_selector, container, false);
        lstviewHQ = (ListView) view.findViewById(R.id.list_hqselector);
        ckLimitUp = (CheckBox) view.findViewById(R.id.ckb_selectLimitUp);
        adapter = new HQSelectorAdapter(this.getActivity());
        lstviewHQ.setAdapter(adapter);
        btnSearch = (Button) view.findViewById(R.id.btn_hqselector_search);
        btnSearch.setOnClickListener(v->{
            List<ArticleDTO> articleDTOs = adapter.getSelected();
            AndStockCodeProider proider = new AndStockCodeProider();
            if(articleDTOs.size()>0){
                for (ArticleDTO articleDTO:articleDTOs) {
                    proider.add(new ArticleStockCodeProider(articleDTO));
                }
            }
            if(ckLimitUp.isChecked()){
                proider.add(new VolumeTopStockCodeProider(200));
            }
            if(proider.size() ==0){
                ToastUtil.show(this.getActivity(),"Please select filter.");
            }
            else{
                StockCodeProider.Proider = proider;
                this.getActivity().startActivity(new Intent(this.getActivity(), HQActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.read();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
