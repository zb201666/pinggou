package cn.itsource.pinggou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zb
 * @version 1.0
 * @classname ProductApplication
 * @description TODO
 * @date 2019/5/16
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "cn.itsource.pinggou.mapper")
@EnableSwagger2  //启用swagger生成接口文档
@EnableFeignClients(basePackages = "cn.itsource.pinggou.client")
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
