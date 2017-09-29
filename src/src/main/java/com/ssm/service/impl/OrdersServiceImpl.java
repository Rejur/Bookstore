package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.mapper.OrdersMapper;
import com.ssm.model.Orders;
import com.ssm.model.Users;
import com.ssm.service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrdersServiceImpl extends BaseService<Orders> implements OrdersService{
    @Autowired
    OrdersMapper ordersMapper;

    public int insertnoid(Orders orders) {
        Integer orders1 = ordersMapper.insertnoid(orders);
        return orders1;
    }

    public List<Orders> selectFPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        return selectAll();
    }
}
