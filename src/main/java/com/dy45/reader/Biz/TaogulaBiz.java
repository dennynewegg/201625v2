package com.dy45.reader.Biz;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.ExceptionUtil;
import com.dy45.reader.Util.HtmlHelper;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.entity.ArticleCategory;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.http.RequestWrapper;

//import org.xml.sax.Parser;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dy45 on 10/19/2016.
 */

public class TaogulaBiz {

    static String articleListUrl = "http://www.taogula.com/forum-47-1.html";

    public static void getArticleList(final OnActionListener1<List<ArticleDTO>> listener){
        RequestWrapper rw = new RequestWrapper();
        rw.getString(articleListUrl,rst->{
            if(!rst.hasError()){
                listener.onAction(parseHtml(rst.getResult()));
            }
        });
    }

    public static List<ArticleDTO> parseHtml(String html) {

        List<ArticleDTO> articles= new ArrayList<>(100);
        try {
            Parser parser = new Parser();

            parser.setInputHTML(html);
            NodeList anodes = parser.parse(new AndFilter(new TagNameFilter("a")
                    ,new HasAttributeFilter("class","s xst")));
            if(anodes == null
                    || anodes.size()==0){
                return articles;
            }

            for (int i = 0;i<anodes.size();i++) {
                Tag tag = ((Tag) (anodes.elementAt(i)));
                ArticleDTO articleInfo = new ArticleDTO();
                articleInfo.setUrl(tag.getAttributeEx("href").getValue());
                if (tag.getChildren() != null) {
                    articleInfo.setName(tag.getChildren().elementAt(0).getText());
                }
                articleInfo.setCatalog(ArticleCategory.Taogula);
                articleInfo.setCatalogCode(ArticleCategory.Taogula);
                articleInfo.setFileType("html");

                articles.add(articleInfo);
                try {
                    if (tag.getParent().getParent().getChildren() != null) {
                        Node tdNode = tag.getParent().getParent().getChildren().elementAt(5);
                        NodeList spanNodes = tdNode.getChildren().extractAllNodesThatMatch(new TagNameFilter("span"));
                        if (spanNodes.size() > 0) {
                            String dateStr= spanNodes.elementAt(0).getChildren().elementAt(0).getText();
                            Date date =  DateUtil.parseDayDate(dateStr.split(" ")[0]);
                            articleInfo.setDate(DateUtil.toDayString(date));
                        }
                    }
                } catch (Exception spEx) {
                    ExceptionUtil.handle(spEx);
                }

            }
        } catch (Exception e) {
            ExceptionUtil.handle(e);
        }
        return articles;
    }

    public static String parseArticleContent(String html){
        String body = HtmlHelper.InnerText(html,new AndFilter(new TagNameFilter("td")
                ,new HasAttributeFilter("class","t_f")));
        return ArticleBiz.ToHtmlContent(body);
    }


    public static void downloadArticle(final ArticleDTO articleDTO, final OnActionListener1<ArticleDTO> listener){
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.getString(articleDTO.getUrl(),stringRestResult -> {
            if (stringRestResult.hasError()) {
                listener.onAction(null);
                return ;
            }
            String htmlContent = stringRestResult.getResult();
            htmlContent = parseArticleContent(htmlContent);
            articleDTO.setFileType("html");
            articleDTO.setFileName(articleDTO.getFileType() + File.separator
                    + String.format(articleDTO.getName() + "." + articleDTO.getFileType()));
            FileUtil.write(articleDTO.getFileName(), htmlContent, false);
            listener.onAction(articleDTO);
        });
    }
}
