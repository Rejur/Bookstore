package com.ssm.mapper;

import com.ssm.model.Books;
import com.ssm.model.Orderbook;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value="orderbookMapper")
public interface OrderbookMapper extends Mapper<Orderbook> {
    int insertnoid(Orderbook orderbook);
}