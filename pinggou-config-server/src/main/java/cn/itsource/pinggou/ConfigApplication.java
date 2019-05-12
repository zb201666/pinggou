package cn.itsource.pinggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zb
 * @version 1.0
 * @classname ConfigApplication
 * @description 配置中心服务器入口类
 * @date 2019/5/12
 */
@SpringBootApplication
//eureka客户端
@EnableEurekaClient
//配置中心服务器
@EnableConfigServer
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
