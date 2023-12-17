package com.stone.order.mapper;

import com.stone.model.entity.order.OrderInfo;
import com.stone.model.entity.order.OrderItem;
import com.stone.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    void saveOrderInfo(OrderInfo orderInfo);

    void saveOrderItem(OrderItem orderItem);

    void saveOrderLog(OrderLog orderLog);

    OrderInfo getById(Long orderId);

    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);

    List<OrderItem> findByOrderId(Long id);

    OrderInfo getByOrderNo(String orderNo);

    void updateById(OrderInfo orderInfo);
}
