package com.dy45.reader.StockCodeProider;

import com.dy45.reader.Biz.ArticleBiz;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.entity.StockDTO;

import java.util.List;

/**
 * Created by dy45 on 10/21/2016.
 */

public class ArticleStockCodeProider implements IStockCodeProider {
    ArticleDTO articleInfo ;

    public ArticleStockCodeProider(ArticleDTO articleDTO){
        articleInfo = articleDTO;
    }

    @Override
    public void getStockCodeList(OnActionListener1<List<String>> listener) {
        ArticleBiz.getArticle(articleInfo,content->{
            List<StockDTO> stocks = ArticleBiz.parseStock(content);
            List<String> codeList =
                    ListUtil.select(stocks,stock-> StringUtil.getShortCode(stock.getCode()));
            listener.onAction(codeList);
        });
    }
}
