package com.dy45.reader.Biz;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.ArticleCategory;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.entity.DayTradeDTO;
import com.dy45.reader.entity.StockDTO;
import com.dy45.reader.sql.DayTradedb;
import com.dy45.reader.sql.Stockdb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 10/20/2016.
 */

public class ArticleBiz {

    public static List<StockDTO> parseStock(String article){
        List<StockDTO> stocks = Stockdb.getStock();
        List<StockDTO> matchStocks = new ArrayList<>(40);
        if(!ListUtil.isNullOrEmpty(stocks)){
            for (StockDTO stock :stocks) {
                if(article.contains(StringUtil.getShortCode(stock.getCode()))
                    ||article.contains(stock.getName())){
                    matchStocks.add(stock);
                }
            }
        }
        return matchStocks;
    }

    public static void getArticle(ArticleDTO articleDTO,OnActionListener1<String> listener1){
        downloadFile(articleDTO,res->{
            String content = "";
            if(res!=null){
                content = FileUtil.read(FileUtil.getPath(articleDTO.getFileName()));
            }
            listener1.onAction(content);
        });
    }

    public static void downloadFile(ArticleDTO articleDTO,OnActionListener1<ArticleDTO> listener1){
        if(FileUtil.exists(FileUtil.getPath(articleDTO.getFileName()))){
            listener1.onAction(articleDTO);
        }
        else if(StringUtil.isEqual(articleDTO.getCatalog(),ArticleCategory.Taogula)){
            TaogulaBiz.downloadArticle(articleDTO,listener1);
        }
        else {
            new HTSCWebBiz().getArticleDetail(null,articleDTO,listener1);
        }
    }

    static  String template = "<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><body><table width=\"360px\"><tr><td>@body</td></tr></table></body></html>";

    public static String ToHtmlContent(String txt){
        return template.replace("@body",txt);
    }
}
