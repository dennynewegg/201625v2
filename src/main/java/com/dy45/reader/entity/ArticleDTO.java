package com.dy45.reader.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by dy45 on 4/24/2015.
 */
public class ArticleDTO implements Serializable {

    public boolean IsSelected;


    @JSONField(name = "name")
    private String name;

    @JSONField(name = "path")
    private String path;

    @JSONField(name = "catalog")
    private String catalog;

    @JSONField(name = "sourceNumber")
    private String sourceNumber ;

    @JSONField(name = "url")
    private String url;

    @JSONField(name = "catalogCode")
    private String catalogCode;

    @JSONField(name = "date")
    private String date;

    @JSONField(name = "fileName")
    private String fileName ;

    @JSONField(name = "fileType")
    private String fileType ;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSourceNumber() {
        return sourceNumber == null?"":sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode;
    }


}
