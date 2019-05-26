package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
public interface IProductTypeService extends IService<ProductType> {

    /**
     * @author zb
     * @description 加载树形结构
     * @date 2019/5/16
     * @name loadTree
     * @param
     * @return java.util.List<cn.itsource.pinggou.domain.ProductType>
     */
    List<ProductType> loadTree();

    /**
     * @author zb
     * @description 生成静态首页
     * @date 2019/5/17
     * @name generateStaticPage
     * @param
     * @return void
     */
    void generateStaticPage();

    /**
     * @author zb
     * @description 根据id查询
     * @date 2019/5/23
     * @name selectOneById
     * @param id
     * @return cn.itsource.pinggou.domain.ProductType
     */
    List<ProductType> selectByPId(Long id);
}
