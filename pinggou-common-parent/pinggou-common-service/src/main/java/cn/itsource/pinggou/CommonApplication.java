package cn.itsource.pinggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zb
 * @version 1.0
 * @classname CommonApplication
 * @description 公共服务启动类
 * @date 2019/5/17
 */
@SpringBootApplication
@EnableEurekaClient
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class,args);
    }
}
