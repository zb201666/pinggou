package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.service.IProductTypeService;
import cn.itsource.pinggou.domain.ProductType;
import cn.itsource.pinggou.query.ProductTypeQuery;
import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductTypeController {
    @Autowired
    public IProductTypeService productTypeService;

    /**
    * 保存和修改公用的
    * @param productType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/productType",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductType productType){
        try {
            if(productType.getId()!=null){
                productTypeService.updateById(productType);
            }else{
                productTypeService.save(productType);
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
    @RequestMapping(value="/productType/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productTypeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/productType/{id}",method = RequestMethod.GET)
    public ProductType get(@PathVariable("id") Long id)
    {
        return productTypeService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/productType/list",method = RequestMethod.GET)
    public List<ProductType> list(){
        return productTypeService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/productType/page",method = RequestMethod.POST)
    public PageList<ProductType> json(@RequestBody ProductTypeQuery query)
    {
        IPage<ProductType> productTypeIPage = productTypeService.page(new Page<>(query.getPage(), query.getSize()));
        return new PageList<>(productTypeIPage.getTotal(),productTypeIPage.getRecords());
    }

    /**
     * @author zb
     * @description 加载树形结构
     * @date 2019/5/16
     * @name loadTree
     * @param
     * @return java.util.List<cn.itsource.pinggou.domain.ProductType>
     */
    @GetMapping("/productType/tree")
    public List<ProductType> loadTree(){
        return productTypeService.loadTree();
    }

    /**
     * @author zb
     * @description 生成静态首页
     * @date 2019/5/17
     * @name generateStaticPage
     * @param
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @PostMapping("/home")
    public AjaxResult generateStaticPage(){
        try {
            productTypeService.generateStaticPage();
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败");
        }
    }
}
