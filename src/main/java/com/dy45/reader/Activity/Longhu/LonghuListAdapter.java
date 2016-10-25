package com.dy45.reader.Activity.Longhu;

import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.HQSinaBiz;
import com.dy45.reader.Biz.LonghuBiz;
import com.dy45.reader.Biz.LonghuFileBiz;
import com.dy45.reader.Biz.LonghuWebBiz;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ColorUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.Util.ReflectUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.LonghuCompare;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.entity.LonghuType;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.sql.DayTradedb;
import com.dy45.reader.sql.Longhudb;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/9/2015.
 */
public class LonghuListAdapter extends BaseAdapter {

    public Date getDate() {
        return date;
    }
    private Date date ;

    private List<LonghuDTO> mList = new ArrayList<LonghuDTO>(100);
    private Context context;

    public LonghuListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDate(Date date){
        if(!DateUtil.isEqual(this.date,date)) {
            this.date = date;
            readLonghuList(date);
            notifyDataSetChanged();
        }
    }

    private void readLonghuList(Date date){
        mList.clear();
        mList.addAll(LonghuFileBiz.readLonghuList(date));
        sort();
    }


    private void sort(){
        if(mList.size()>0){
            Collections.sort(mList,new Comparator<LonghuDTO>() {
                @Override
                public int compare(LonghuDTO lhs, LonghuDTO rhs) {
                    double lnet = lhs.getBuyAmount()-lhs.getSellAmount();
                    double rnet = rhs.getBuyAmount()-rhs.getSellAmount();
                    if(lnet == rnet){
                        return 0;
                    }
                    if(lnet == 0){
                        return  1;
                    }
                    if(rnet == 0){
                        return -1;
                    }
                    return Double.compare(rnet,lnet);
                }
            });
        }
    }


    public void refresh(final OnActionListener listener) {

        LonghuBiz biz = new LonghuBiz(this.context,this.getDate(),new OnActionListener1<List<LonghuDTO>>() {
            @Override
            public void onAction(List<LonghuDTO> longhuDTOs) {
                mList.clear();
                mList.addAll(longhuDTOs);
                sort();
                notifyDataSetChanged();
                listener.onAction();
            }
        }, new OnActionListener1<LonghuDTO>() {
            @Override
            public void onAction(LonghuDTO longhuDTO) {
                mList.add(longhuDTO);
                notifyDataSetChanged();
            }
        });
        biz.refreshLonghuList();
    }

    private List<LonghuDTO> getNotExists(List<LonghuDTO> list){
        if(mList.size()==0){
            return list;
        }
        List<LonghuDTO> longhuDTOs = new ArrayList<>(list.size());
        for (LonghuDTO item1:list){
            boolean isFind = false;
            for(LonghuDTO item2:mList ){
                String code = StringUtil.getShortCode(item2.getCode());
                if(item1.getCode()==code
                    && LonghuType.isSameType(item1.getType(),item2.getType())){
                    isFind = true;
                    break;
                }
            }
            if(!isFind){
                longhuDTOs.add(item1);
            }
        }
        return longhuDTOs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null
           || convertView.getTag() ==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.longhu_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setData(mList.get(position));
        return convertView;
    }

    public void clear() {
        mList.clear();
    }

    static class ViewHolder{
        private TextView name;
        private TextView code;
        private TextView type;
        private TextView close;
        private TextView buy;
        private TextView sell;
        private TextView net;
        private TextView rate;

        public ViewHolder(View view){
            name = (TextView) view.findViewById(R.id.longhu_list_item_name);
            code= (TextView) view.findViewById(R.id.longhu_list_item_code);
            type = (TextView) view.findViewById(R.id.longhu_list_item_type);
            close = (TextView) view.findViewById(R.id.longhu_list_item_close);
            buy = (TextView) view.findViewById(R.id.longhu_list_item_buy);
            sell = (TextView) view.findViewById(R.id.longhu_list_item_sell);
            net = (TextView) view.findViewById(R.id.longhu_list_item_net);
            rate = (TextView) view.findViewById(R.id.longhu_list_item_rate);
        }

        private void setData(LonghuDTO longhuDTO){
            name.setText(longhuDTO.getName());
            code.setText(longhuDTO.getCode());
            type.setText(LonghuType.GetString(longhuDTO.getType()));
            close.setText(String.valueOf(longhuDTO.getClose()));
            rate.setText(longhuDTO.getRateString());
            buy.setText(String.valueOf ((int) longhuDTO.getBuyAmount()));
            sell.setText(String.valueOf ((int) longhuDTO.getSellAmount()));
            net.setText(String.valueOf((int) longhuDTO.getNetAmount()));

            int color = ColorUtil.getColor(longhuDTO.getNetAmount());
            net.setTextColor(color);
            buy.setTextColor(color);
        }
    }
}
