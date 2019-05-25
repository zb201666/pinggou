package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.service.ISpecificationService;
import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.query.SpecificationQuery;
import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecificationController {
    @Autowired
    public ISpecificationService specificationService;

    /**
    * 保存和修改公用的
    * @param specification  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/specification",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Specification specification){
        try {
            if(specification.getId()!=null){
                specificationService.updateById(specification);
            }else{
                specificationService.save(specification);
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
    @RequestMapping(value="/specification/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            specificationService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/specification/{id}",method = RequestMethod.GET)
    public Specification get(@PathVariable("id") Long id)
    {
        return specificationService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/specification/list",method = RequestMethod.GET)
    public List<Specification> list(){
        return specificationService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/specification/page",method = RequestMethod.POST)
    public PageList<Specification> json(@RequestBody SpecificationQuery query) {
        IPage<Specification> specificationIPage = specificationService.page(new Page<>(query.getPage(), query.getSize()));
        return new PageList<>(specificationIPage.getTotal(),specificationIPage.getRecords());
    }

    /**
     * @author zb
     * @description 根据商品id查询对应的商品属性
     * @date 2019/5/25
     * @name loadSpecificationsByProductTypeId
     * @param productTypeId
     * @return cn.itsource.pinggou.util.PageList<cn.itsource.pinggou.domain.Specification>
     */
    @RequestMapping(value = "/specification/productType",method = RequestMethod.GET)
    public List<Specification> loadSpecificationsByProductTypeId(Long productTypeId) {
        return specificationService.loadSpecificationsByProductTypeId(productTypeId);
    }
}
