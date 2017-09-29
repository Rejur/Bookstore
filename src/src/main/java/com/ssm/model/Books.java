package com.ssm.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class Books {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Author")
    private String author;

    @Column(name = "PublisherId")
    private Integer publisherid;

    @Column(name = "PublishDate")
    private Date publishdate;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "UnitPrice")
    private BigDecimal unitprice;

    @Column(name = "ContentDescription")
    private String contentdescription;

    @Column(name = "TOC")
    private String toc;

    @Column(name = "CategoryId")
    private Integer categoryid;

    @Column(name = "Clicks")
    private Integer clicks;

    /**
     * @return Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return PublisherId
     */
    public Integer getPublisherid() {
        return publisherid;
    }

    /**
     * @param publisherid
     */
    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    /**
     * @return PublishDate
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * @param publishdate
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * @return ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return UnitPrice
     */
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice
     */
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * @return ContentDescription
     */
    public String getContentdescription() {
        return contentdescription;
    }

    /**
     * @param contentdescription
     */
    public void setContentdescription(String contentdescription) {
        this.contentdescription = contentdescription;
    }

    /**
     * @return TOC
     */
    public String getToc() {
        return toc;
    }

    /**
     * @param toc
     */
    public void setToc(String toc) {
        this.toc = toc;
    }

    /**
     * @return CategoryId
     */
    public Integer getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     */
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return Clicks
     */
    public Integer getClicks() {
        return clicks;
    }

    /**
     * @param clicks
     */
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}