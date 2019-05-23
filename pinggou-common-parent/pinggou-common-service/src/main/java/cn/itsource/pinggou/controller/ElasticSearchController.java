package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.domain.ProductDoc;
import cn.itsource.pinggou.repository.ProductDocRepository;
import cn.itsource.pinggou.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @classname ElasticSearchController
 * @description es服务
 * @date 2019/5/23
 */
@RestController
public class ElasticSearchController {

    @Autowired
    private ProductDocRepository repository;

    /**
     * @author zb
     * @description 保存一个
     * @date 2019/5/23
     * @name save
     * @param productDoc
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/save")
    public AjaxResult save(@RequestBody ProductDoc productDoc){
        try {
            repository.save(productDoc);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败！！！原因是"+e.getMessage());
        }
    };


    /**
     * @author zb
     * @description 批量保存
     * @date 2019/5/23
     * @name saveBatch
     * @param productDocs
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/saveBatch")
    public AjaxResult saveBatch(@RequestBody List<ProductDoc> productDocs){
        try {
            repository.saveAll(productDocs);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败！！！原因是"+e.getMessage());
        }
    };


    /**
     * @author zb
     * @description 删除一个
     * @date 2019/5/23
     * @name remove
     * @param id
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/remove")
    public AjaxResult remove(@RequestParam("id") Long id){
        try {
            repository.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败！！！原因是"+e.getMessage());
        }
    };


    /**
     * @author zb
     * @description 批量删除
     * @date 2019/5/23
     * @name removeBatch
     * @param productDocs
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/es/removeBatch")
    public AjaxResult removeBatch(@RequestBody List<ProductDoc> productDocs){
        try {
            repository.deleteAll(productDocs);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除失败！！！原因是"+e.getMessage());
        }
    };
}
