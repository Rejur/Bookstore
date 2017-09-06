package com.book.mapper;

import com.book.pojo.Userroles;

public interface UserrolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userroles record);

    int insertSelective(Userroles record);

    Userroles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userroles record);

    int updateByPrimaryKey(Userroles record);
}