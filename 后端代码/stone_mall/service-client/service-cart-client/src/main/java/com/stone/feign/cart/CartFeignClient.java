package com.stone.feign.cart;

import com.stone.model.entity.h5.CartInfo;
import com.stone.model.vo.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-cart")
public interface CartFeignClient {
    @Operation(summary="选中的购物车")
    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    List<CartInfo> getAllChecked();
    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    Result deleteChecked() ;
}
