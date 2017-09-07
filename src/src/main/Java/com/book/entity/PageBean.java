package com.book.entity;

import java.util.List;

import com.book.util.PageData;
public class PageBean {
    private List<PageData> pageDataList;
    public List<PageData> getPageDataList() {return pageDataList;}
    public void setPageDataList(List<PageData> pds) {this.pageDataList = pds;}
}
