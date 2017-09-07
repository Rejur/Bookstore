package com.book.pojo;

import com.book.pojo.Searchkeywords;

public interface SearchkeywordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Searchkeywords record);

    int insertSelective(Searchkeywords record);

    Searchkeywords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Searchkeywords record);

    int updateByPrimaryKey(Searchkeywords record);
}