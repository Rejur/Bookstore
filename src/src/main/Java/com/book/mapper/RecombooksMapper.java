package com.book.mapper;

import com.book.pojo.Recombooks;

public interface RecombooksMapper {
    int insert(Recombooks record);

    int insertSelective(Recombooks record);
}