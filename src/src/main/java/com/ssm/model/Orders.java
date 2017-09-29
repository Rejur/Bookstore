package com.ssm.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "OrderDate")
    private Date orderdate;

    @Column(name = "UserId")
    private Integer userid;

    @Column(name = "TotalPrice")
    private BigDecimal totalprice;

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
     * @return OrderDate
     */
    public Date getOrderdate() {
        return orderdate;
    }

    /**
     * @param orderdate
     */
    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
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

    /**
     * @return TotalPrice
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
}