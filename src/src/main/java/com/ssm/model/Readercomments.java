package com.ssm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ReaderComments")
public class Readercomments {
    @Id
    @Column(name = "ReaderName")
    private String readername;

    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BookId")
    private Integer bookid;

    @Column(name = "Title")
    private String title;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Date")
    private Date date;

    /**
     * @return ReaderName
     */
    public String getReadername() {
        return readername;
    }

    /**
     * @param readername
     */
    public void setReadername(String readername) {
        this.readername = readername;
    }

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
     * @return BookId
     */
    public Integer getBookid() {
        return bookid;
    }

    /**
     * @param bookid
     */
    public void setBookid(Integer bookid) {
        this.bookid = bookid;
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
     * @return Comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}