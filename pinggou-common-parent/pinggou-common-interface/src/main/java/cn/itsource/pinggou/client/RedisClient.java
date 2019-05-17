package cn.itsource.pinggou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zb
 * @version 1.0
 * @classname RedisClient
 * @description redis缓存公共接口
 * @date 2019/5/17
 */
@FeignClient(value = "PINGGOU-COMMON")//标注服务提供者是谁
public interface RedisClient {

    /**
     * @author zb
     * @description 设置数据到缓存
     * @date 2019/5/17
     * @name set
     * @param key
     * @param value
     * @return void
     */
    @PostMapping("/redis")
    void set(@RequestParam("key") String key, @RequestParam("value")String value);

    /**
     * @author zb
     * @description 从服务中取得数据
     * @date 2019/5/17
     * @name get
     * @param key
     * @return java.lang.String
     */
    @GetMapping("/redis")
    String get(@RequestParam("key") String key);
}
