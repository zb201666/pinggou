package cn.itsource.pinggou.client;

import cn.itsource.pinggou.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zb
 * @version 1.0
 * @classname RedisClientFallBackFactory
 * @description 返回托底数据的回调函数
 * @date 2019/5/18
 */
@Component
public class RedisClientFallBackFactory implements FallbackFactory<RedisClient> {
    @Override
    public RedisClient create(Throwable throwable) {
        return new RedisClient() {
            @Override
            public AjaxResult set(String key, String value) {
                return AjaxResult.me().setSuccess(false).setMessage("访问超时，请重试！！！");
            }

            @Override
            public String get(String key) {
                return "[]";//表示未获取到数据
            }
        };
    }
}
