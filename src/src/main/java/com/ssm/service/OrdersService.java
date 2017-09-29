package com.ssm.service;

import com.ssm.model.Orders;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    int insertnoid(Orders orders);

    List<Orders> selectFPage(int page, int rows);
}
