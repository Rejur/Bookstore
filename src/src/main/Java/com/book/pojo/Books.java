package com.book.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Books {
    private Integer id;

    private String title;

    private String author;

    private Integer publisherid;

    private Date publishdate;

    private String isbn;

    private BigDecimal unitprice;

    private String contentdescription;

    private String toc;

    private Integer categoryid;

    private Integer clicks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public String getContentdescription() {
        return contentdescription;
    }

    public void setContentdescription(String contentdescription) {
        this.contentdescription = contentdescription == null ? null : contentdescription.trim();
    }

    public String getToc() {
        return toc;
    }

    public void setToc(String toc) {
        this.toc = toc == null ? null : toc.trim();
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}