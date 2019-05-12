package cn.itsource.pinggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zb
 * @version 1.0
 * @classname PlatApplication
 * @description 平台服务入口类
 * @date 2019/5/11
 */
@SpringBootApplication
//表示Eureka客户端
@EnableEurekaClient
public class PlatApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatApplication.class, args);
    }
}
