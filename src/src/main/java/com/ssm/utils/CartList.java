package com.ssm.utils;

import java.math.BigDecimal;

public class CartList {
    private String title;
    private String isbn;
    private BigDecimal unitprice;
    private Integer quantity;
    private Integer bookid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public CartList(String title, String isbn, BigDecimal unitprice, Integer quantity, Integer bookid) {
        this.title = title;
        this.isbn = isbn;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.bookid = bookid;
    }

    public CartList() {}

}
