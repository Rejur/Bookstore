package com.ssm.model;

import javax.persistence.*;

@Table(name = "SearchKeywords")
public class Searchkeywords {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Keyword")
    private String keyword;

    @Column(name = "SearchCount")
    private Integer searchcount;

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
     * @return Keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return SearchCount
     */
    public Integer getSearchcount() {
        return searchcount;
    }

    /**
     * @param searchcount
     */
    public void setSearchcount(Integer searchcount) {
        this.searchcount = searchcount;
    }
}