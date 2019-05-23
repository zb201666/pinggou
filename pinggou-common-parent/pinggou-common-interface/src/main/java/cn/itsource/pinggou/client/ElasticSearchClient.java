package cn.itsource.pinggou.client;

import cn.itsource.pinggou.domain.ProductDoc;
import cn.itsource.pinggou.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @classname ElasticSearchClient
 * @description es公共服务
 * @date 2019/5/23
 */
@FeignClient(value = "PINGGOU-COMMON")
public interface ElasticSearchClient {

    /**
     * @author zb
     * @description 保存一个
     * @date 2019/5/23
     * @name save
     * @param productDoc
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/save")
    AjaxResult save(@RequestBody ProductDoc productDoc);


    /**
     * @author zb
     * @description 批量保存
     * @date 2019/5/23
     * @name saveBatch
     * @param productDocs
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/saveBatch")
    AjaxResult saveBatch(@RequestBody List<ProductDoc> productDocs);


    /**
     * @author zb
     * @description 删除一个
     * @date 2019/5/23
     * @name remove
     * @param id
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/remove")
    AjaxResult remove(@RequestParam("id") Long id);


    /**
     * @author zb
     * @description 批量删除
     * @date 2019/5/23
     * @name removeBatch
     * @param productDocs
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/removeBatch")
    AjaxResult removeBatch(@RequestBody List<ProductDoc> productDocs);

}
