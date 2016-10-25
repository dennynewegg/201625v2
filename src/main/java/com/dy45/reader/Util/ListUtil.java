package com.dy45.reader.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 4/24/2015.
 */
public class ListUtil {
    public  static boolean isNullOrEmpty(List list ){
        return list==null || list.size()==0;
    }

    public static <T> List<T> findAll(List<T> list,OnFunListener1<T,Boolean> funListener1){
        if(isNullOrEmpty(list)
                || funListener1 == null){
            return new ArrayList<>();
        }
        List<T> findList = new ArrayList<>(list.size());
        for (T item:list){
            if(funListener1.Fun(item)){
                findList.add(item);
            }
        }
        return findList;
    }

    public static <T> T find(List<T> list,OnFunListener1<T,Boolean> funListener1){
        if(isNullOrEmpty(list)
                || funListener1 == null){
            return null;
        }
        for (T item:list){
            if(funListener1.Fun(item)){
                return item;
            }
        }
        return null;
    }

    public static <T> boolean exists(List<T> list,OnFunListener1<T,Boolean> funListener1){
        T item = find(list,funListener1);
        return item != null;
    }

    public static <T,R> List<R> select(List<T> orgList,final OnFunListener1<T,R> listener){
        if(orgList == null){
            return new ArrayList<R>(0);
        }
        List<R> resultList = new ArrayList<R>(orgList.size());
        for (T item : orgList) {
            resultList.add(listener.Fun(item));
        }
        return resultList;
    }

//    public static void sort(List<T> orgList,final )


    public static <T> void forRange(List<T> list,int maxRange,OnActionListener1<List<T>> listOnActionListener1 ){
        if(!isNullOrEmpty(list)){
            int maxloop = (list.size()+maxRange-1)/maxRange;
            for (int index=0;index<maxloop;index++){

                int startIndex = index * maxRange;
                int count = maxRange;

                if (startIndex + count > list.size())
                {
                    count = list.size() - startIndex;
                }
                List<T> range = list.subList(startIndex,startIndex+count);
                listOnActionListener1.onAction(range);
            }
        }
    }
}
