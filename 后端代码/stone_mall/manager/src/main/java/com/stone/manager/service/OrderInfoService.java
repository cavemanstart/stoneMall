package com.stone.manager.service;

import com.stone.model.dto.order.OrderStatisticsDto;
import com.stone.model.vo.order.OrderStatisticsVo;

public interface OrderInfoService {
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
