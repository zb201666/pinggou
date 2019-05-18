package cn.itsource.pinggou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("127.0.0.1", 6379);
        config.setPassword(RedisPassword.of("196691"));//身份认证
        return new JedisConnectionFactory(config);
    }
}

