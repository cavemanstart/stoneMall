package com.stone.order.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.h5.OrderInfoDto;
import com.stone.model.entity.order.OrderInfo;
import com.stone.model.vo.h5.TradeVo;

public interface OrderInfoService {
    TradeVo getTrade();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfo(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    OrderInfo getByOrderNo(String orderNo) ;

    void updateOrderStatus(String orderNo, Integer orderStatus);
}
