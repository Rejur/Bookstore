package com.book.pojo;

import com.book.pojo.Bookratings;

public interface BookratingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bookratings record);

    int insertSelective(Bookratings record);

    Bookratings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bookratings record);

    int updateByPrimaryKey(Bookratings record);
}