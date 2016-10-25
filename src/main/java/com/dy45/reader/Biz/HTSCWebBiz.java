package com.dy45.reader.Biz;


import android.content.Context;

import com.dy45.reader.File.FileUtil;
import com.dy45.reader.Util.DateUtil;
import com.dy45.reader.Util.HandlerUtil;
import com.dy45.reader.Util.HtmlHelper;
import com.dy45.reader.Util.KeyValue;
import com.dy45.reader.Util.ListUtil;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.ArticleDTO;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.http.RequestWrapper;

import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy45 on 4/24/2015.
 */
public class HTSCWebBiz {

    public String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    //    request.addHeader("Content-Type" ,"application/x-www-form-urlencoded");
//    public static final String articleListBaseUrl = "http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzx&catId=%1s&productCode=%2s";
    public static final String articleDetailBaseUrl="http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzxArticle&docIdNew=%1s";
    public static final String pdfBaseUrl="http://www.htsc.com.cn%1s";
    public static final String regex = "<li><span class=\"date\">([^<]*)</span><a\\s+href=\"javascript:gotoArticle\\(\\'(\\w+)\\',\\'(\\w+)\\'\\);\">([^<]+)</a>\\s+</li>";
    public static String PDFFilePath = "PDFFile";

    public static KeyValue[] ArticleCatalogList = new KeyValue[]{
            new KeyValue("JingNiu" ,"http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzx&menuName=wdsrgw&catId=30&productCode=SP0222"),
            new KeyValue("LongHu","http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzx&menuName=wdsrgw&catId=6&productCode=SP0295"),
            new KeyValue("早知道","http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzx&menuName=wdsrgw&catId=41&productCode=SP0285"),
            new KeyValue("上证决策参考","http://www.htsc.com.cn/htzq/cmsAction.do?method=queryArticleForWdzx&menuName=wdsrgw&catId=41&productCode=SP0286")    };

    public static List<ArticleDTO> parseArticleList(String htmlContent,String catalog){
        List<ArticleDTO> articleList = new ArrayList<>(20);
        Pattern pattern=Pattern.compile(regex,Pattern.UNICODE_CASE|Pattern.MULTILINE);
        Matcher matcher =pattern.matcher(htmlContent);
        while (matcher.find()){
            ArticleDTO articleDTO = new ArticleDTO();
            if(matcher.group(1).matches("\\d{4}-\\d{2}-\\d{2}")){
                articleDTO.setDate(matcher.group(1));
            }
            else{
                articleDTO.setDate(DateUtil.toString(new Date(), "yyyy-MM-dd"));
            }
            articleDTO.setUrl(matcher.group(2));
            articleDTO.setCatalogCode(matcher.group(3));
            articleDTO.setCatalog(catalog);
            articleDTO.setName(matcher.group(4));
            articleDTO.setSourceNumber(String.format("%1s_%2s_%3s",catalog,articleDTO.getDate(),articleDTO.getUrl()));
            if(catalog.equalsIgnoreCase("早知道")
                    || catalog.equalsIgnoreCase("上证决策参考")){
                articleDTO.setFileType("html");
            }
            else{
                articleDTO.setFileType("pdf");
            }
            articleList.add(articleDTO);
        }
        return articleList;
    }

    public void getArticleList(Context context, final OnActionListener1<List<ArticleDTO>> listener){
        getArticleList(context, 0, new ArrayList<ArticleDTO>(), new OnActionListener1<List<ArticleDTO>>() {
            @Override
            public void onAction(final List<ArticleDTO> articleDTOs) {
                HandlerUtil.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onAction(articleDTOs);
                        }
                    }
                });
            }
        });
    }

    private void getArticleList(final Context context
            , final int index
            , final List<ArticleDTO> resultList
            , final OnActionListener1<List<ArticleDTO>> listener) {

        if (index >= ArticleCatalogList.length) {
            listener.onAction(resultList);
            return;
        }
        final KeyValue articleCatalog = ArticleCatalogList[index];
        final String articleListUrl = articleCatalog.Value;

        RequestWrapper requestWrapper = getRequestWrapper();
        requestWrapper.getString(articleListUrl, stringRestResult -> {
            if (!stringRestResult.hasError()) {
                String articleHtml = stringRestResult.getResult();
                if (StringUtil.isEmpty(articleHtml)
                        || !articleHtml.contains("欢迎您")) {
                    HTSCWebBiz.this.setCookie("");
                    ToastUtil.show(context, "请重新登录");
                    listener.onAction(resultList);
                    return;
                }
                if (!StringUtil.isEmpty(stringRestResult.getCookie())) {
                    HTSCWebBiz.this.setCookie(stringRestResult.getCookie());
                }
                List<ArticleDTO> articleList = HTSCWebBiz.parseArticleList(articleHtml, articleCatalog.Key);
                if (!ListUtil.isNullOrEmpty(articleList)) {
                    resultList.addAll(articleList);
                }
            }
            getArticleList(context, index + 1, resultList, listener);
        });
    }

    private RequestWrapper getRequestWrapper() {
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.addCookie(cookie);
        requestWrapper.addHeader("Content-Type", "application/x-www-form-urlencoded");
        return requestWrapper;
    }

    private boolean hasRight(String htmlContent,Context context){
        if(StringUtil.isEmpty((htmlContent))
            || htmlContent.contains("通讯密码")){
            this.setCookie("");
            ToastUtil.show(context,"请重新登录");
            return  false;
        }
        return true;
    }


    public void getArticleDetail(final Context context
            , final ArticleDTO articleDTO
            , final OnActionListener1<ArticleDTO> listener){
        String detailUrl = String.format(articleDetailBaseUrl,articleDTO.getUrl());
        RequestWrapper requestWrapper = getRequestWrapper();
        requestWrapper.getString(detailUrl, stringRestResult -> {
            String htmlContent = stringRestResult.getResult();
            if(!hasRight(htmlContent,context)){
                listener.onAction(null);
                return ;
            }
            String pdfUrl = parsePdfUrl(htmlContent);
            if(!StringUtil.isEmpty(pdfUrl)){
                pdfUrl = String.format(pdfBaseUrl,pdfUrl);
                downLoadPdf(context,pdfUrl,articleDTO,listener);
                return;
            }
            else{
                htmlContent = parseArticleContent(htmlContent);
                articleDTO.setFileType("html");
                articleDTO.setFileName(articleDTO.getFileType()+File.separator
                        +String.format(articleDTO.getSourceNumber()+"."+articleDTO.getFileType()));
                FileUtil.write(articleDTO.getFileName(),htmlContent,false);
                listener.onAction(articleDTO);
            }
        });
    }

    private void downLoadPdf(final Context context
            ,String pdfUrl
            , final ArticleDTO articleDTO
            , final OnActionListener1<ArticleDTO> listener){
        articleDTO.setFileType("pdf");
        articleDTO.setFileName(PDFFilePath+File.separator+ String.format(articleDTO.getSourceNumber()+"."+articleDTO.getFileType()));
        RequestWrapper requestWrapper = getRequestWrapper();
        requestWrapper.getBytes(context,pdfUrl, restResult -> {
            if (!restResult.hasError()) {
                byte[] bytes = restResult.getResult();
                if (bytes != null && bytes.length > 0) {
                    FileUtil.write(articleDTO.getFileName(), bytes, false);
                    listener.onAction(null);
                    return;
                }
            }
            listener.onAction(null);
        });
    }

    private String parsePdfUrl(String htmlContent){
        if(!StringUtil.isEmpty(htmlContent)) {
            Pattern pattern = Pattern.compile("window.location.href=\\\"([^\\\"]+)\\\";");
            Matcher matcher = pattern.matcher(htmlContent);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return "";
    }

    public static String parseArticleContent(String html){
        String  body =  HtmlHelper.InnerText(html,new AndFilter(new TagNameFilter("span")
                ,new HasAttributeFilter("id","ShowContent")));
        return ArticleBiz.ToHtmlContent(body);
    }

}
