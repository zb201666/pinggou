package cn.itsource.pinggou.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author zb
 * @version 1.0
 * @classname TemplateClient
 * @description TODO
 * @date 2019/5/17
 */
@FeignClient(value = "PINGGOU-COMMON")//标注服务提供者是谁
public interface TemplateClient {

    /**
     * @author zb
     * @description 根据模板生成静态页面
     * @date 2019/5/17
     * @name generateStaticPage
     * @param params
     * @return void
     */
    @PostMapping("/page")
    void generateStaticPage(@RequestBody Map<String,Object> params);
}
