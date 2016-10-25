package com.dy45.reader.Activity.HQSelector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dy45.articlereader.R;
import com.dy45.reader.Activity.Article.ArticleAdapter;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.ArticleDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dy45 on 10/21/2016.
 */

public class HQSelectorAdapter extends BaseAdapter {

    Context context;
    List<ArticleDTO> list = new ArrayList<>(20);

    public HQSelectorAdapter(Context context){
        this.context = context;
    }

    public void read(){
        String articleJson = FileUtil.read(ArticleAdapter.ARTICLE_FILE_NAME);
        if(!StringUtil.isEmpty(articleJson)) {
            list = JSON.parseArray(articleJson,ArticleDTO.class);
            list = ListUtil.findAll(list,item->

                    item.getFileType().equalsIgnoreCase("html")
            );

            Collections.sort(list, (lhs, rhs) ->
                    DateUtil.parseDayDate(rhs.getDate()).compareTo(
                            DateUtil.parseDayDate(lhs.getDate())));
        }
        notifyDataSetChanged();
    }

    public List<ArticleDTO> getSelected(){
        return ListUtil.findAll(list,articleDTO -> articleDTO.IsSelected);
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null
                || convertView.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.hqselector_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ArticleDTO dayTradeDTO = (ArticleDTO) getItem(position);
        holder.bindData(dayTradeDTO);
        return convertView;
    }

    class ViewHolder{
        CheckBox ckSelect;
        ArticleDTO articleDTO;

        public ViewHolder(View view){
            ckSelect = (CheckBox) view.findViewById(R.id.ckb_selectorArticle);
            ckSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(articleDTO!= null){
                    articleDTO.IsSelected = isChecked;
                }
            });
        }

        public void bindData(ArticleDTO articleDTO){
            ckSelect.setText(articleDTO.getName());
            ckSelect.setChecked(articleDTO.IsSelected);
            this.articleDTO = articleDTO;
        }
    }

}
