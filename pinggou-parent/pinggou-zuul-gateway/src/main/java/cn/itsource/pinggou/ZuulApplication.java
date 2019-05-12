package cn.itsource.pinggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zb
 * @version 1.0
 * @classname ZuulApplication
 * @description Zuul网关入口类
 * @date 2019/5/11
 */
@SpringBootApplication
//表示是一个Eureka客户端
@EnableEurekaClient
//表示是一个网关
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
