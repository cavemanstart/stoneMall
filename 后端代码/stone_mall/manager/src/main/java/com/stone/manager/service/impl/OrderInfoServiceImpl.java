package com.stone.manager.service.impl;
import cn.hutool.core.date.DateUtil;
import com.stone.manager.mapper.OrderStatisticMapper;
import com.stone.manager.service.OrderInfoService;
import com.stone.model.dto.order.OrderStatisticsDto;
import com.stone.model.entity.order.OrderStatistics;
import com.stone.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderStatisticMapper orderStatisticMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        List<OrderStatistics> list =  orderStatisticMapper.getOrderStatisticsData(orderStatisticsDto);
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        List<String> dateList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        for(OrderStatistics item : list) {
            dateList.add(DateUtil.format(item.getOrderDate(),"yyyy-MM-dd"));
            amountList.add(item.getTotalAmount());
        }
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);
        return orderStatisticsVo;
    }
}
