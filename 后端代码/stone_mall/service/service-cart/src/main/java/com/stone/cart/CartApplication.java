package com.stone.cart;

import com.stone.anotation.EnableUserTokenFeignInterceptor;
import com.stone.anotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients (basePackages = {"com.stone.feign.product"})
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
public class CartApplication {
    public static void main(String [] args){
        SpringApplication.run(CartApplication.class,args);
    }
}
