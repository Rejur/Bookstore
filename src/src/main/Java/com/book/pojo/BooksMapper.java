package com.book.pojo;

import com.book.pojo.Books;

public interface BooksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);
}