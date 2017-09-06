package com.book.mapper;

import com.book.pojo.Publishers;

public interface PublishersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Publishers record);

    int insertSelective(Publishers record);

    Publishers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Publishers record);

    int updateByPrimaryKey(Publishers record);
}