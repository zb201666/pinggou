package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.util.RedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zb
 * @version 1.0
 * @classname RedisController
 * @description TODO
 * @date 2019/5/17
 */
@RestController
public class RedisController {

    /**
     * @author zb
     * @description 将数据设置到redis中
     * @date 2019/5/17
     * @name set
     * @param key
     * @param value
     * @return void
     */
    @PostMapping("/redis")
    public void set(String key,String value){
        RedisUtils.INSTANCE.set(key, value);
    }

    /**
     * @author zb
     * @description 从redis中取出数据
     * @date 2019/5/17
     * @name get
     * @param key
     * @return java.lang.String
     */
    @GetMapping("/redis")
    public String get(String key){
        return RedisUtils.INSTANCE.get(key);
    }
}
