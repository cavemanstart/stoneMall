package com.stone.pay;

import com.stone.anotation.EnableUserTokenFeignInterceptor;
import com.stone.anotation.EnableUserWebMvcConfiguration;
import com.stone.pay.properties.AlipayProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.stone.feign.order",
        "com.stone.feign.product"
})
@EnableConfigurationProperties(value = { AlipayProperties.class })
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
public class PayApplication {
    public static void main(String [] args){
        SpringApplication.run(PayApplication.class, args);
    }
}
