package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.OrderbookMapper;
import com.ssm.model.Orderbook;
import com.ssm.service.OrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderServiceImpl")
public class OrderBookServiceImpl extends BaseService<Orderbook> implements OrderBookService{
    @Autowired
    OrderbookMapper orderbookMapper;

    public int insertnoid(Orderbook orderbook) {
        return orderbookMapper.insertnoid(orderbook);
    }

    public List<Orderbook> selectFPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        return selectAll();
    }
}
