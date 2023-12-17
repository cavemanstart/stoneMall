package com.stone.manager.mapper;

import com.stone.model.dto.order.OrderStatisticsDto;
import com.stone.model.entity.order.OrderStatistics;
import com.stone.model.vo.order.OrderStatisticsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticMapper {
    void insert(OrderStatistics orderStatistics);


    List<OrderStatistics> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
