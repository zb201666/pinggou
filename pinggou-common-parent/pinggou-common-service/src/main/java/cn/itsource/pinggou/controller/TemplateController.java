package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zb
 * @version 1.0
 * @classname TemplateController
 * @description TODO
 * @date 2019/5/17
 */
@RestController
public class TemplateController {

    /**
     * @author zb
     * @description 根据模板生成静态页面
     * @date 2019/5/17
     * @name generateStaticPage
     * @param params
     * @return void
     */
    @PostMapping("/page")
    public void generateStaticPage(@RequestBody Map<String,Object> params){
        Object model = params.get("model");//数据对象
        String templatePath = (String) params.get("templatePath");//模板文件的物理路径
        String targetPath = (String) params.get("targetPath");//目标输出文件的物理路径
        VelocityUtils.staticByTemplate(model, templatePath, targetPath);
    }
}
