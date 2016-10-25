package com.dy45.reader.Activity.Chart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Longhu.DatePickFragment;
import com.dy45.reader.Util.ColorUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.MathUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.LonghuCompare;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.entity.LonghuSummaryDTO;
import com.dy45.reader.sql.Longhudb;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by dy45 on 5/21/2015.
 */
public class LonghuDetailFragment extends Fragment {

    public final static String Code_Param = "Code";
    LonghuDetailAdpter adpter ;
    ListView listView;
    View btnDate ;
    TextView dateView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        adpter = new LonghuDetailAdpter(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_longhu_detail, container, false);
        listView = (ListView) view.findViewById(R.id.longhu_detail_list);
        View headerview = getActivity().getLayoutInflater().inflate(R.layout.longhu_detail_item,null,false);
        listView.addHeaderView(headerview);
        listView.setAdapter(adpter);

        btnDate = view.findViewById(R.id.longhu_detail_date_container);
        dateView = (TextView) view.findViewById(R.id.longhu_detail_date_view);

        Bundle bundle = getArguments();
        String code=null;
        if(bundle!=null) {
            code = getArguments().getString(Code_Param);
        }
        if(!StringUtil.isEmpty(code)) {
            adpter.setCode(code);
        }
        else {
            btnDate.setVisibility(View.VISIBLE);
            Date date = Longhudb.getMaxLonghuDate();
            if(!DateUtil.isDate(date)){
                date = DateUtil.getLastDate();
            }
            setDate(dateView,date);

            btnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickFragment.show(LonghuDetailFragment.this
                            ,DateUtil.parseDayDate(dateView.getText().toString())
                            ,"Date"
                            ,new OnActionListener1<Date>() {
                        @Override
                        public void onAction(Date date) {
                            setDate(dateView,date);
                        }
                    });
                }
            });
        }
        return view;
    }

    private void setDate(TextView view ,Date date){
        view.setText(DateUtil.toDayString(date));
        adpter.setDate(date);
    }


    @Override
    public void onStart() {
        super.onStart();
        if(adpter.getCount()==0) {
            adpter.refresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.adpter.clear();
    }

    public class LonghuDetailAdpter extends BaseAdapter{
        private List<LonghuDTO> mList = new ArrayList<>();
        private Context context;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String code;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            if(date!=null
                && (this.date==null
                || this.date.compareTo(date)!=0)) {
                this.date = date;
                this.refresh();
            }
        }

        private Date date;

        public LonghuDetailAdpter(Context context) {
            this.context = context;
        }

        public void refresh(){
            this.clear();
            List<LonghuDTO> list = null;
            if(!StringUtil.isEmpty(this.code)) {
                list = Longhudb.getLonghuListByCode(this.code);
            }
            else if(DateUtil.isDate(date)){
                list = Longhudb.getLonghuByDate(date);
            }
            if (!ListUtil.isNullOrEmpty(list)) {
                mList.addAll(list);
                sort();
            }
            notifyDataSetChanged();
        }

        private void sort(){
            Collections.sort(mList,new LonghuCompare());
        }


        private void remove(LonghuDTO longhuDTO){
            if(mList.size()>0){
                mList.remove(longhuDTO);
                notifyDataSetChanged();
            }
        }

        private boolean canRemove(final LonghuDTO longhuDTO){
            if(longhuDTO == null){
                return false;
            }

            List<LonghuDTO> list =ListUtil.findAll(mList,new OnFunListener1<LonghuDTO, Boolean>() {
                @Override
                public Boolean Fun(LonghuDTO item) {
                    return longhuDTO!=item
                            && longhuDTO.isSameCode(item)
                            && longhuDTO.getBuyAmount()==item.getBuyAmount()
                            && longhuDTO.getNetAmount() == item.getNetAmount();

                }
            });
            return list.size()>0;
        }



        public void clear(){
            this.mList.clear();
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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView == null
                    || convertView.getTag() ==null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.longhu_detail_item, null);
                holder = new ViewHolder(convertView,position);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setData(mList.get(position));
            return convertView;
        }

        class ViewHolder{
            private TextView date;
//            private TextView type;
            private TextView name;
            private TextView code;
            private TextView avgcost;
            private TextView buy;
            private TextView sell;
            private TextView net;
            private TextView rate;
            private ImageButton btnDel;
            private int position;

            public ViewHolder(View view,int position){
//                type = (TextView) view.findViewById(R.id.longhu_detail_item_type);
                avgcost = (TextView) view.findViewById(R.id.longhu_detail_item_close);
                buy = (TextView) view.findViewById(R.id.longhu_detail_item_buy);
                sell = (TextView) view.findViewById(R.id.longhu_detail_item_sell);
                net = (TextView) view.findViewById(R.id.longhu_detail_item_net);
                date = (TextView) view.findViewById(R.id.longhu_detail_item_Date);
                btnDel = (ImageButton) view.findViewById(R.id.longhu_detail_item_delete);
                code = (TextView) view.findViewById(R.id.longhu_detail_item_code);
                name = (TextView) view.findViewById(R.id.longhu_detail_item_name);

                this.position = position;
                btnDel.setVisibility(View.VISIBLE);
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = ViewHolder.this.position;
                        LonghuDetailAdpter adpter1 = LonghuDetailAdpter.this;
                        if (pos >= 0
                                && pos < adpter1.getCount()) {
                            final LonghuDTO longhuDTO =
                                    (LonghuDTO) adpter1.getItem(pos);
                            if (longhuDTO != null
                                    && longhuDTO.getRowid() > 0) {
                                final int rowid = longhuDTO.getRowid();
                                new AlertDialog.Builder(LonghuDetailAdpter.this.context)
                                        .setTitle("Delete")
                                        .setMessage("Are you to delete?")
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Longhudb.deleteLongHuByID(rowid);
                                                LonghuDetailAdpter.this.remove(longhuDTO);
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        })
                                        .show();
                            }
                        }
                    }
                });
            }

            private void setData(LonghuDTO longhuDTO){
//                type.setText(LonghuType.GetString(longhuDTO.getType()));
                if(longhuDTO.getNetAmount()==0){
                    longhuDTO.setNetAmount(longhuDTO.getBuyAmount()-longhuDTO.getSellAmount());
                }

                buy.setText(String.valueOf ((int) longhuDTO.getBuyAmount()));
                sell.setText(String.valueOf ((int) longhuDTO.getSellAmount()));
                net.setText(String.valueOf((int) longhuDTO.getNetAmount()));
                date.setText(DateUtil.toDayString(longhuDTO.getDate()));
                avgcost.setText(String.valueOf(MathUtil.toDouble2(longhuDTO.getAvgCost())));
                name.setText(longhuDTO.getName());
                code.setText(longhuDTO.getCode());

                int color = ColorUtil.getColor(longhuDTO.getNetAmount());
                net.setTextColor(color);
                buy.setTextColor(color);
                avgcost.setTextColor(color);
                sell.setTextColor(color);
            }
        }
    }
}
