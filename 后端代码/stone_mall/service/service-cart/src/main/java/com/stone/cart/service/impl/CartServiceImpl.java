package com.stone.cart.service.impl;

import com.alibaba.fastjson2.JSON;
import com.stone.cart.service.CartService;
import com.stone.feign.product.ProductFeignClient;
import com.stone.model.entity.h5.CartInfo;
import com.stone.model.entity.product.ProductSku;
import com.stone.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate<String, String > redisTemplate;
    @Autowired
    private ProductFeignClient productFeignClient;
    private String getCartKey(Long userId) {
        return "user:cart:" + userId;
    }
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        //获取当前用户信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);
        //从redis查询是否存在数据
        Object CartInfoObject = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));

        //存在，修改商品数量
        CartInfo cartInfo = null;
        if(CartInfoObject!=null){
            cartInfo = JSON.parseObject(CartInfoObject.toString(), CartInfo.class);
            cartInfo.setSkuNum(skuNum+cartInfo.getSkuNum());
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        }
        else{
            cartInfo = new CartInfo();

            //远程调用product模块的接口获取productSku信息
            ProductSku productSku = productFeignClient.getSkuBySkuId(skuId);
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        //将修改后的数据存入redis
        redisTemplate.opsForHash().put(cartKey,skuId.toString(), JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> list() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(valueList)){
            return valueList.stream().map(value -> JSON.parseObject(value.toString(), CartInfo.class))
                    .sorted((a,b)->b.getCreateTime().compareTo(a.getCreateTime()))
                    .toList();
        }
        return new ArrayList<>();
    }

    @Override
    public void deleteCartBySkuId(Long skuId) {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        redisTemplate.opsForHash().delete(cartKey,String.valueOf(skuId));
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        Object ob = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        if(ob!=null){
            CartInfo cartInfo = JSON.parseObject(ob.toString(), CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            cartInfo.setUpdateTime(new Date());
            redisTemplate.opsForHash().put(cartKey,String.valueOf(skuId),JSON.toJSONString(cartInfo));
        }
    }

    @Override
    public void allCheckCart(Integer isAllChecked) {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(valueList)){
            List<CartInfo> cartInfoList = valueList.stream().map(value -> JSON.parseObject(value.toString(), CartInfo.class))
                    .toList();
            cartInfoList.forEach(cartInfo -> {
                cartInfo.setIsChecked(isAllChecked);
                cartInfo.setUpdateTime(new Date());
                redisTemplate.opsForHash().put(cartKey,cartInfo.getSkuId().toString(),JSON.toJSONString(cartInfo));
            });

        }
    }

    @Override
    public void clearCart() {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        redisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllChecked() {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        List<CartInfo> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(valueList)){
            list = valueList.stream().map(item -> JSON.parseObject(item.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public void deleteChecked() {
        String cartKey = getCartKey(AuthContextUtil.getUserInfo().getId());
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        if(!CollectionUtils.isEmpty(valueList)){
            valueList.stream().map(cartInfo -> JSON.parseObject(cartInfo.toString(),CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked()==1)
                    .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey,String.valueOf(cartInfo.getSkuId())));
        }
    }
}
