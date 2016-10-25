package com.dy45.reader.Biz;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.LonghuDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/26/2015.
 */
public class LonghuFileBiz {
    public final static String ListBaseFilePath = "Longhu"+ File.separator+"List";
    public final static String DetailBaseFilePath ="Longhu"+File.separator+"detail";

    public static boolean isMainComName(LonghuDTO longhuDTO){
        return  longhuDTO!=null
                &&!StringUtil.isEmpty(longhuDTO.getComName())
                &&longhuDTO.getComName().contains("机构专用");
    }

    public static List<LonghuDTO> readLonghuList(Date date){
        String filePath = getLonghuListPath(date);
        List<LonghuDTO> list = FileUtil.readArray(filePath, LonghuDTO.class);
        if(list==null){
            list = new ArrayList<>();
        }
        return  list;
    }

    public static String getLonghuDetailPath(String code,Date date,String type){
        return DetailBaseFilePath
                + File.separator+ DateUtil.toDayString(date)
                +File.separator+StringUtil.getShortCode(StringUtil.getShortCode(code))+"_"+type+".txt";
    }

    public static String getLonghuListPath(Date date){
        return  ListBaseFilePath
                + File.separator + DateUtil.toString(date, "yyyy-MM")
                + File.separator + DateUtil.toDayString(date)+".txt";
    }


    public static void writeLonghuDetail(List<LonghuDTO> list){
        if(!ListUtil.isNullOrEmpty(list)) {
            LonghuDTO first = list.get(0);
            String filePath = getLonghuDetailPath(first.getCode(),first.getDate(), first.getType());
            FileUtil.writeObject(filePath,list);
        }
    }

    public static boolean existsLonghuDetail(String code,Date date,String type){
        File file = new File(FileUtil.getPath(getLonghuDetailPath(code,date,type)));
        return file.exists();
    }

    public static void writeLonghuList(List<LonghuDTO> list,Date date){
        if(DateUtil.isBizDay(date)
                && DateUtil.isDate(date))
        {
            if(list.size()>0) {
                String dateFilePath =getLonghuListPath(date);
                FileUtil.writeObject(dateFilePath, list);
            }
        }
    }



}
