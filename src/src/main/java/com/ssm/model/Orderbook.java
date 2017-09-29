package com.ssm.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "OrderBook")
public class Orderbook {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "OrderID")
    private Integer orderid;

    @Column(name = "BookID")
    private Integer bookid;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "UnitPrice")
    private BigDecimal unitprice;

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
     * @return OrderID
     */
    public Integer getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     */
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    /**
     * @return BookID
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
     * @return Quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
}