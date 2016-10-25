package com.dy45.reader.Activity.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.HQSinaBiz;
import com.dy45.reader.StockCodeProider.IStockCodeProider;
import com.dy45.reader.StockCodeProider.VolumeTopStockCodeProider;
import com.dy45.reader.StockCodeProider.ZiXuanStockCodeProider;
import com.dy45.reader.Util.ColorUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.DayTradeDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 4/29/2015.
 */
public class HQAdapter extends BaseAdapter {

    private List<DayTradeDTO> mList;
    private Context context;
    private HQType hqtype;
    ZiXuanStockCodeProider zixuanProider = new ZiXuanStockCodeProider();
    public IStockCodeProider limitUpProider = new VolumeTopStockCodeProider();

    public HQAdapter(Context context) {
        this.mList = new ArrayList<>(30);
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

    public void Refresh(final OnActionListener1<String> listener){
        getCodeList(strings -> {
            if(strings!=null
                    && strings.size()>0) {
                HQSinaBiz.getSinaHQ(strings, listRestResult -> {
                    if (listRestResult.hasError()) {
                        listener.onAction(listRestResult.getError().getMessage());
                        return;
                    }
                    List<DayTradeDTO> list = listRestResult.getResult();
                    Collections.sort(list, (lhs, rhs) -> rhs.getRate().compareTo(lhs.getRate()));
                    setList(list);
                    listener.onAction(null);
                });
            }
            else{
                ToastUtil.show(this.context,"No Code");
                listener.onAction(null);
            }
        });
    }


    public void addCode(String code){
        String[] codes = code.split(",");
        zixuanProider.add(Arrays.asList(codes));
    }


    public Date getMaxDate(){
        if(mList.size()>0){
            DayTradeDTO dayTradeDTO =Collections.max(mList,(lhs, rhs)
                    -> lhs.getDate().compareTo(rhs.getDate()));
            return dayTradeDTO.getDate();
        }
        return null;
    }

    public void setList(List<DayTradeDTO> list){
        if(!ListUtil.isNullOrEmpty(list)){
            mList.clear();
            mList.addAll(list);
            this.notifyDataSetChanged();
        }
    }

    public HQType getHqtype() {
        return hqtype;
    }

    public void setHqtype(HQType hqtype) {
        this.hqtype = hqtype;
    }


    private void getCodeList(final OnActionListener1<List<String>>  listener1 ){
        if(getHqtype() == HQType.LoveStock){
            zixuanProider.getStockCodeList(listener1);
        }
        else {
            limitUpProider.getStockCodeList(listener1);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null
                    || convertView.getTag() == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.hq_list_item, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DayTradeDTO dayTradeDTO = (DayTradeDTO) getItem(position);
            holder.setHQ(dayTradeDTO);
        return convertView;
    }


    class ViewHolder{
        ViewHolder(View view) {
            codeTextView = (TextView) view.findViewById(R.id.hq_list_item_code);
            nameTextView = (TextView) view.findViewById(R.id.hq_list_item_name);
            closeTextView = (TextView) view.findViewById(R.id.hq_list_item_close);
            raseTextView = (TextView) view.findViewById(R.id.hq_list_item_rate);
            openTextView = (TextView) view.findViewById(R.id.hq_list_item_open);
            lowTextView = (TextView) view.findViewById(R.id.hq_list_item_low);
            highTextView = (TextView) view.findViewById(R.id.hq_list_item_high);

        }

        public void setHQ(DayTradeDTO dayTradeDTO){
            this.dayTradeDTO = dayTradeDTO;
            codeTextView.setText(dayTradeDTO.getCode());
            nameTextView.setText(dayTradeDTO.getName());
            closeTextView.setText(String.valueOf(dayTradeDTO.getClose()));
            raseTextView.setText(String.valueOf(dayTradeDTO.getRateString()));
            openTextView.setText(String.valueOf(dayTradeDTO.getOpen()));
            highTextView.setText(String.valueOf(dayTradeDTO.getHigh()));
            lowTextView.setText(String.valueOf(dayTradeDTO.getLow()));
            int color = ColorUtil.getColor(dayTradeDTO.getRate());

            setTextColor(color);
        }

        private void setTextColor(int color){
            codeTextView.setTextColor(color);
            nameTextView.setTextColor(color);
            closeTextView.setTextColor(color);
            raseTextView.setTextColor(color);
            openTextView.setTextColor(color);
            highTextView.setTextColor(color);
            lowTextView.setTextColor(color);
        }


        public TextView codeTextView;
        public TextView nameTextView;
        public TextView closeTextView;
        public TextView raseTextView;
        public TextView openTextView;
        public TextView highTextView;
        public TextView lowTextView;
        public ImageButton btnDel;
        public DayTradeDTO dayTradeDTO;
    }

    public enum HQType{
        LoveStock,
        FetchLimitUp
    }
}

