package cn.itsource.pinggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @classname EurekaApplication
 * @description Eureka服务器入口类
 * @author zb
 * @date 2019/5/11
 * @version 1.0
 */
@SpringBootApplication
//表示一个Eureka服务器
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
