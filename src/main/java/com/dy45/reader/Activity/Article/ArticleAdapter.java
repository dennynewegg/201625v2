package com.dy45.reader.Activity.Article;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.dy45.articlereader.R;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.StringUtil;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by dy45 on 4/24/2015.
 */
public class ArticleAdapter extends BaseAdapter {

    public static final int MAX_ARTICLE_COUNT = 500;
    public static final String ARTICLE_FILE_NAME="Article/ArticleList.txt";

    List<ArticleDTO> mList = new ArrayList<>(20);
    Context context ;

    public ArticleAdapter(Context context){
        this.context = context;
    }

    public void read(){
        String articleJson = FileUtil.read(ARTICLE_FILE_NAME);
        if(!StringUtil.isEmpty(articleJson)) {
            List<ArticleDTO> list = JSON.parseArray(articleJson,ArticleDTO.class);
            setList(list);
        }
    }

    public void write(){
        if(!ListUtil.isNullOrEmpty(mList)){
            sort();
            if(mList.size()>MAX_ARTICLE_COUNT){
                mList = mList.subList(0,MAX_ARTICLE_COUNT);
            }
            String json = JSON.toJSONString(mList);
            FileUtil.write(ARTICLE_FILE_NAME,json,false);
        }
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
                || convertView.getTag() == null){
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.article_list_item,null);
            holder = new ViewHolder();
            holder.ArticleName = (TextView) convertView.findViewById(R.id.article_list_item_name);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        ArticleDTO articleDTO = (ArticleDTO) getItem(position);
        holder.ArticleName.setText(articleDTO.getName());
        return convertView;
    }

    public void setList(List<ArticleDTO> list){
        if(!ListUtil.isNullOrEmpty(list)){
            if(mList.size()>0){
                for(ArticleDTO item :mList ){
                    for(int index = list.size()-1;index>-1;index--){
                        ArticleDTO articleDTO2=list.get(index);
                        if(item.getUrl().equals(articleDTO2.getUrl())){
                            list.remove(index);
                        }
                    }
                }
            }
            mList.addAll(list);
            sort();
            this.notifyDataSetChanged();
        }
    }

    private void sort(){
        Collections.sort(mList,new Comparator<ArticleDTO>() {
            @Override
            public int compare(ArticleDTO lhs, ArticleDTO rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
            }
        });
    }

    public void clear(){
        mList.clear();
        this.notifyDataSetChanged();
    }

    static class ViewHolder{
        public TextView ArticleName;

    }
}
