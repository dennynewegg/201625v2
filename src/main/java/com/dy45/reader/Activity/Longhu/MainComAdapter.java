package com.dy45.reader.Activity.Longhu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.HQSinaBiz;
import com.dy45.reader.Control.HVListView;
import com.dy45.reader.Util.ColorUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.MathUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.entity.LonghuSummaryDTO;
import com.dy45.reader.sql.Longhudb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/15/2015.
 */
public class MainComAdapter extends BaseAdapter {

    private List<LonghuSummaryDTO> mList;
    private Context context;
    public MainComAdapter(Context context){
        this.context = context;
        mList = new ArrayList<>(100);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    public void refresh(Date from ,Date to, final OnActionListener listener){
        mList.clear();
        List<LonghuSummaryDTO> mainLonghuList = Longhudb.queryLonghuList(from, to);
        if(!ListUtil.isNullOrEmpty(mainLonghuList)){
            mList.addAll(mainLonghuList);

            List<LonghuSummaryDTO> buyList=ListUtil.findAll(mainLonghuList,new OnFunListener1<LonghuSummaryDTO, Boolean>() {
                @Override
                public Boolean Fun(LonghuSummaryDTO longhuSummaryDTO) {
                    return longhuSummaryDTO.getNetAmount()>0;
                }
            });

            HQSinaBiz.fillCurrentTrade(buyList,0,new OnActionListener() {
                @Override
                public void onAction() {

                    if(mList.size()>0){
                        for (LonghuSummaryDTO item:mList){
                            if(item.getClose()==0){
                                item.setClose(item.getAvgCost());
                            }
                            double gainValue = 0;
                            if(item.getNetQty()>0
                                    && item.getClose()>0){
                                double avgCost = item.getNetAmount()/item.getNetQty();
                                if(avgCost >0) {
                                    gainValue = 100*((item.getClose() - avgCost) /avgCost);
                                    item.setGain(gainValue);
                                }
                            }
                        }
                    }

                    sort();
                    notifyDataSetChanged();
                    listener.onAction();
                }
            });
        }
        else{
            notifyDataSetChanged();
            listener.onAction();
        }
    }

    public void clear(){
        mList.clear();
    }


    private void sort(){
        if(mList.size()>0){
            Collections.sort(mList,new Comparator<LonghuSummaryDTO>() {
                @Override
                public int compare(LonghuSummaryDTO lhs, LonghuSummaryDTO rhs) {
                    return Double.compare(rhs.getNetAmount(),lhs.getNetAmount());
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null
                || convertView.getTag() ==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.longhu_maincomsummary_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(mList.get(position));
        if(parent instanceof HVListView){
            holder.scallTo(((HVListView)parent).getHeadScrollX());
        }
        return convertView;
    }

    static class ViewHolder{
        private TextView name;
        private TextView code;
        private TextView type;
        private TextView buy;
        private TextView sell;
        private TextView net;
        private TextView gain;
        private TextView esp;
        private TextView close;
        private TextView rate;
        private TextView currentShare;
        private TextView memo;
        private TextView buycount;
        private View hvControl;

        public ViewHolder(View view){
            name = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_name);
            code= (TextView) view.findViewById(R.id.longhu_maincomsummary_item_code);
            buy = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_buyamount);
            sell = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_sellamount);
            net = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_netamount);
            gain = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_gain);
            esp= (TextView) view.findViewById(R.id.longhu_maincomsummary_item_EPS);
            close = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_close);
            rate = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_rate);
            currentShare = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_CurrentShare);
            buycount = (TextView) view.findViewById(R.id.longhu_maincomsummary_item_buycount);
            hvControl = view.findViewById(R.id.mainCom_summary_HV);
        }

        private void setData(LonghuSummaryDTO longhuDTO){
            name.setText(longhuDTO.getName());
            code.setText(longhuDTO.getCode());
            sell.setText(String.valueOf(((int)longhuDTO.getSellAmount())));
            buy.setText(String.valueOf(((int)longhuDTO.getBuyAmount())));
            net.setText(String.valueOf(((int)(longhuDTO.getBuyAmount()-longhuDTO.getSellAmount()))));
            rate.setText(longhuDTO.getRateString());
            close.setText(String.valueOf(MathUtil.toDouble2(longhuDTO.getClose())));
            if(longhuDTO.getBuyCount()>0) {
                buycount.setText(String.valueOf(longhuDTO.getBuyCount()));
            }

            double gainValue = longhuDTO.getGain();
            if(longhuDTO.getNetAmount()>0) {
                gain.setText(MathUtil.toString(gainValue) + "%");
            }

            double colorvalue = longhuDTO.getNetAmount()/Math.abs(longhuDTO.getNetAmount());
            if(colorvalue>0){
                colorvalue = colorvalue*(50-gainValue);
            }
            int color = ColorUtil.getColor(colorvalue);
            buy.setTextColor(color);
            net.setTextColor(color);
            sell.setTextColor(color);
            gain.setTextColor(color);
        }

        public void scallTo(int x){
            hvControl.scrollTo(x,0);
        }
    }
}
