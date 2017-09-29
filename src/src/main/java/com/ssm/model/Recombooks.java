package com.ssm.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "RecomBooks")
public class Recombooks {
    @Column(name = "BookId")
    private Integer bookid;

    @Column(name = "UserId")
    private Integer userid;

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
     * @return UserId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}