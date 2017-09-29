package com.ssm.mapper;

import com.ssm.model.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value="ordersMapper")
public interface OrdersMapper extends Mapper<Orders> {
    Integer insertnoid(Orders orders);
}