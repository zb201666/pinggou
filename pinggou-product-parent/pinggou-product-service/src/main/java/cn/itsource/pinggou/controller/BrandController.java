package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.service.IBrandService;
import cn.itsource.pinggou.domain.Brand;
import cn.itsource.pinggou.query.BrandQuery;
import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.LetterUtil;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BrandController {
    @Autowired
    public IBrandService brandService;

    /**
    * 保存和修改公用的
    * @param brand  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/brand",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Brand brand){
        try {
            if(brand.getId()!=null){
                //设置更新时间
                brand.setUpdateTime(new Date().getTime());
                brandService.updateById(brand);
            }else{
                //设置首字母
                String firstLetter = LetterUtil.getFirstLetter(brand.getName());
                brand.setFirstLetter(firstLetter);
                //设置创建时间
                brand.setCreateTime(new Date().getTime());
                brandService.save(brand);
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
    @RequestMapping(value="/brand/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            brandService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }


    /**
     * @author zb
     * @description 批量删除
     * @date 2019/5/17
     * @name batchDelete
     * @param ids
     * @return cn.itsource.pinggou.util.AjaxResult
     */
    @RequestMapping(value="/brand/batch/{ids}",method=RequestMethod.DELETE)
    public AjaxResult batchDelete(@PathVariable("ids") List<Long> ids){
        try {
            brandService.removeByIds(ids);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/brand/{id}",method = RequestMethod.GET)
    public Brand get(@PathVariable("id") Long id)
    {
        return brandService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/brand/list",method = RequestMethod.GET)
    public List<Brand> list(){
        return brandService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/brand/page",method = RequestMethod.POST)
    public PageList<Brand> json(@RequestBody BrandQuery query) {
        Page<Brand> page = new Page<>(query.getPage(), query.getSize());
        IPage<Brand> brandIPage = brandService.selectByQuery(page,query);
        return new PageList<Brand>(brandIPage.getTotal(),brandIPage.getRecords());
    }
}
