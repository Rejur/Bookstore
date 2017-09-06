package com.book.mapper;

import com.book.pojo.Sysdiagrams;

public interface SysdiagramsMapper {
    int deleteByPrimaryKey(Integer diagramId);

    int insert(Sysdiagrams record);

    int insertSelective(Sysdiagrams record);

    Sysdiagrams selectByPrimaryKey(Integer diagramId);

    int updateByPrimaryKeySelective(Sysdiagrams record);

    int updateByPrimaryKeyWithBLOBs(Sysdiagrams record);

    int updateByPrimaryKey(Sysdiagrams record);
}