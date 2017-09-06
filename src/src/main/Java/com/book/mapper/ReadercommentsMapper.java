package com.book.mapper;

import com.book.pojo.Readercomments;

public interface ReadercommentsMapper {
    int deleteByPrimaryKey(String readername);

    int insert(Readercomments record);

    int insertSelective(Readercomments record);

    Readercomments selectByPrimaryKey(String readername);

    int updateByPrimaryKeySelective(Readercomments record);

    int updateByPrimaryKey(Readercomments record);
}