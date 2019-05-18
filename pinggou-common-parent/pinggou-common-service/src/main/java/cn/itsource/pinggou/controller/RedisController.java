package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

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
    public AjaxResult set(String key, String value){
        try {
            //RedisUtils.INSTANCE.set(key, value);
            ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key, value);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败，原因是："+e.getMessage());
        }
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
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
       // return RedisUtils.INSTANCE.get(key);
        return opsForValue.get(key);
    }
}
