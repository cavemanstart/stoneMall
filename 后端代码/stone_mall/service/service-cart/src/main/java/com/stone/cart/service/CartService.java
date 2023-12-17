package com.stone.cart.service;

import com.stone.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {
    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> list();

    void deleteCartBySkuId(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void allCheckCart(Integer isAllChecked);

    void clearCart();

    List<CartInfo> getAllChecked();

    void deleteChecked();
}
