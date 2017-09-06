package com.book.mapper;

import com.book.pojo.Temporarycart;

public interface TemporarycartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Temporarycart record);

    int insertSelective(Temporarycart record);

    Temporarycart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Temporarycart record);

    int updateByPrimaryKey(Temporarycart record);
}