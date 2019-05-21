package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.service.IProductService;
import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    public IProductService productService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/product",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                product.setCreateTime(new Date().getTime());
                productService.save(product);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/product/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable("id") Long id)
    {
        return productService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/product/list",method = RequestMethod.GET)
    public List<Product> list(){
        return productService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/product/page",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query) {
//        IPage<Product> productIPage = productService.page(new Page<>(query.getPage(), query.getSize()));
//        return new PageList<>(productIPage.getTotal(),productIPage.getRecords());
        return productService.selectByQuery(query);
    }


    /**
     * @author zb
     * @description 根据id修改媒体信息
     * @date 2019/5/21
     * @name updateMedias
     * @param product
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/product/updateMedias")
    public AjaxResult updateMedias(@RequestBody Product product){
        try {
            productService.updateMedias(product.getId(),product.getMedias());
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("更新失败！！！原因是："+e.getMessage());
        }
    }

    /**
     * @author zb
     * @description 获取显示属性
     * @date 2019/5/21
     * @name viewProperties
     * @param productId
     * @return java.util.List<cn.itsource.pinggou.domain.Specification>
     */
    @GetMapping("/product/viewProperties/{id}")
    public List<Specification> viewProperties(@PathVariable("id") Long productId){
        return productService.loadViewProperties(productId);
    }


    /**
     * @author zb
     * @description 保存显示属性
     * @date 2019/5/21
     * @name viewProperties
     * @param product
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/product/viewProperties")
    public AjaxResult viewProperties(@RequestBody Product product){
        try {
            productService.saveViewProperties(product);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存失败！！！原因是："+e.getMessage());
        }
    }
}
