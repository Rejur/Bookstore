package com.ssm.service;

import com.ssm.model.Orderbook;

import java.util.List;

public interface OrderBookService extends IService<Orderbook>{
    int insertnoid(Orderbook orderbook);

    List<Orderbook> selectFPage(int page, int rows);
}
