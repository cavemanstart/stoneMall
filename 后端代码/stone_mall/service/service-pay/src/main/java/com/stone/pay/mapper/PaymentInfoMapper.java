package com.stone.pay.mapper;

import com.stone.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentInfoMapper {
    

    void save(PaymentInfo paymentInfo);

    PaymentInfo getByOrderNo(String orderNo);

    void updateById(PaymentInfo paymentInfo);
}
