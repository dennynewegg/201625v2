package com.dy45.reader.Biz;

import android.content.Context;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.OnFunListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.LonghuDTO;
import com.dy45.reader.sql.DayTradedb;
import com.dy45.reader.sql.Longhudb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 5/12/2015.
 */
public class LonghuBiz {

    private Context context;
    private Date date;
    private OnActionListener1<List<LonghuDTO>> finishListener;

    public LonghuBiz(Context context, Date date, OnActionListener1<List<LonghuDTO>> finishListener, OnActionListener1<LonghuDTO> itemChangeListener) {
        this.context = context;
        this.date = date;
        this.finishListener = finishListener;
        this.itemChangeListener = itemChangeListener;
    }

    private OnActionListener1<LonghuDTO> itemChangeListener;

    private List<LonghuDTO> allLonghuList = new ArrayList<>(100);
    private List<LonghuDTO> allMainComList = new ArrayList<>(100);
    private List<LonghuDTO> newLonghuList = new ArrayList<>(100);


    private void OnItemChanged(LonghuDTO longhuDTO){
        if(itemChangeListener!=null){
            itemChangeListener.onAction(longhuDTO);
        }
    }

    private void OnFinished(){
        if(finishListener!=null){
            finishListener.onAction(allLonghuList);
        }
    }

   public void refreshLonghuList(){
       if(date==null
               || date.compareTo(new Date())>0){
           OnFinished();
           return;
       }

       allLonghuList  = LonghuFileBiz.readLonghuList(date);
       LonghuWebBiz.getLonghuList(context,date,new OnActionListener1<List<LonghuDTO>>() {
           @Override
           public void onAction(List<LonghuDTO> longhuDTOs) {
               if(ListUtil.isNullOrEmpty(longhuDTOs)){
                   OnFinished();
                   return ;
               }
               final List<LonghuDTO> notExistsList = ListUtil.findAll(longhuDTOs, new OnFunListener1<LonghuDTO, Boolean>() {
                   @Override
                   public Boolean Fun(final LonghuDTO newItem) {
                       return !ListUtil.exists(allLonghuList, new OnFunListener1<LonghuDTO, Boolean>() {
                           @Override
                           public Boolean Fun(final LonghuDTO oldItem) {
                               return StringUtil.isEqual(StringUtil.getShortCode(oldItem.getCode()), newItem.getCode())
                                       && StringUtil.isEqual(oldItem.getType(), newItem.getType());
                           }
                       });
                   }
               });
               if (ListUtil.isNullOrEmpty(notExistsList)) {
                   OnFinished();
                   return;
               }
               newLonghuList.addAll(notExistsList);
               fillDayTrade(new OnActionListener() {
                   @Override
                   public void onAction() {
                       downloadLonghuDetail(notExistsList);
                   }
               });
           }
       });
   }

    private void downloadLonghuDetail(List<LonghuDTO> longhuDTOList){
        if(!ListUtil.isNullOrEmpty(longhuDTOList)){
            this.allMainComList = Longhudb.getLonghuByDate(date);
        }
        if(allMainComList == null){
            allMainComList = new ArrayList<>();
        }
        downloadLonghuDetail(newLonghuList.size()-1);
    }

    private void fillDayTrade(final OnActionListener listener){
        List<LonghuDTO> notDyList = new ArrayList<>();
        for(LonghuDTO dy:newLonghuList){
            DayTradeDTO dayTradeDTO = DayTradedb.getDayTrade(dy.getCode(), dy.getDate());
            if(dayTradeDTO ==null){
                notDyList.add(dy);
            }
            else{
                HQSinaBiz.copyTo(dayTradeDTO,dy);
            }
        }
        HQSinaBiz.fillCurrentTrade(notDyList,0,new OnActionListener() {
            @Override
            public void onAction() {
                listener.onAction();
            }
        });
    }

    private void downloadLonghuDetail(final int index){
        if(index<0
                || ListUtil.isNullOrEmpty(newLonghuList)){
            OnFinished();
            return;
        }
        final LonghuDTO longhuDTO = newLonghuList.get(index);
        if(!LonghuFileBiz.existsLonghuDetail(longhuDTO.getCode(), longhuDTO.getDate(), longhuDTO.getType())) {
            LonghuWebBiz.getLonghuDetail(context, longhuDTO, new OnActionListener1<List<LonghuDTO>>() {
                @Override
                public void onAction(List<LonghuDTO> detailList) {
                    if (!ListUtil.isNullOrEmpty(detailList)) {
                        LonghuFileBiz.writeLonghuDetail(detailList);
                        getMainComAmount(longhuDTO,detailList);
                    }
                    downloadLonghuDetail(index-1);
                    return;
                }
            });
        }
        else{
            if(longhuDTO.getNetAmount()==0
                    &&longhuDTO.getBuyAmount()==0
                    &&longhuDTO.getSellAmount() == 0) {
                List<LonghuDTO> detailList =
                        readLonghuDetail(longhuDTO.getCode(), longhuDTO.getDate(), longhuDTO.getType());
                if(!ListUtil.isNullOrEmpty(detailList)){
                    getMainComAmount(longhuDTO,detailList);
                }
            }
            downloadLonghuDetail(index - 1);
            return;
        }
    }


    public List<LonghuDTO> readLonghuDetail(String code,Date date,String type){
        String filePath = LonghuFileBiz.getLonghuDetailPath(code,date,type);
        return FileUtil.readArray(filePath, LonghuDTO.class);
    }

    private void getMainComAmount(final LonghuDTO longhuDTO,List<LonghuDTO> detailList) {
        List<LonghuDTO> mainList = ListUtil.findAll(detailList
                , new OnFunListener1<LonghuDTO, Boolean>() {
            @Override
            public Boolean Fun(final LonghuDTO item) {
                return new Boolean(LonghuFileBiz.isMainComName(item))
                        && !ListUtil.exists(allMainComList,new OnFunListener1<LonghuDTO, Boolean>() {
                    @Override
                    public Boolean Fun(LonghuDTO itemInAll) {
                        return itemInAll.isEqual(item);
                    }
                });
            }
        });

        if(!ListUtil.isNullOrEmpty(mainList)) {
            allMainComList.addAll(mainList);
        }
        mainList = ListUtil.findAll(allMainComList,new OnFunListener1<LonghuDTO, Boolean>() {
            @Override
            public Boolean Fun(LonghuDTO item) {
                return item.isSameCode(longhuDTO);
            }
        });

        if(!ListUtil.isNullOrEmpty(mainList)){
            Longhudb.insertLonghuList(mainList);
            double buyAmount = 0;
            double sellAmount = 0;
            for (LonghuDTO item:mainList){
                buyAmount+=item.getBuyAmount();
                sellAmount+=item.getSellAmount();
            }
            longhuDTO.setBuyAmount(buyAmount);
            longhuDTO.setSellAmount(sellAmount);
            longhuDTO.setNetAmount(buyAmount - sellAmount);
        }
        OnItemChanged(longhuDTO);

    }
}
