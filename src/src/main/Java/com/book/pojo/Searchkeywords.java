package com.book.pojo;

public class Searchkeywords {
    private Integer id;

    private String keyword;

    private Integer searchcount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getSearchcount() {
        return searchcount;
    }

    public void setSearchcount(Integer searchcount) {
        this.searchcount = searchcount;
    }
}