package com.book.mapper;

import com.book.pojo.Orderbook;

public interface OrderbookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orderbook record);

    int insertSelective(Orderbook record);

    Orderbook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Orderbook record);

    int updateByPrimaryKey(Orderbook record);
}