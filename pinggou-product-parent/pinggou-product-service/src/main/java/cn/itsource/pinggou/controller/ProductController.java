package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.domain.Product;
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
}
