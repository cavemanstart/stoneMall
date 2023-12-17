package com.stone.order;

import com.stone.anotation.EnableUserTokenFeignInterceptor;
import com.stone.anotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients (basePackages = {"com.stone.feign.cart","com.stone.feign.product","com.stone.feign.user"})
@EnableUserTokenFeignInterceptor
@EnableUserWebMvcConfiguration//为了得到当前 userInfo
public class OrderApplication {
    public static void main(String [] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
