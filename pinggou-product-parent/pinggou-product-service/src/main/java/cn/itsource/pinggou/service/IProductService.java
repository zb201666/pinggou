package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
public interface IProductService extends IService<Product> {

    /**
     * @author zb
     * @description 带有高级查询的分页
     * @date 2019/5/20
     * @name selectByQuery
     * @param
     * @return cn.itsource.pinggou.util.PageList<cn.itsource.pinggou.domain.Product>
     */
    PageList<Product> selectByQuery(ProductQuery query);

    /**
     * @author zb
     * @description 根据id修改媒体信息
     * @date 2019/5/21
     * @name updateMedias
     * @param id
     * @param medias
     * @return void
     */
    void updateMedias(Long id, String medias);

    /**
     * @author zb
     * @description 取得商品的显示属性
     * @date 2019/5/21
     * @name loadViewProperties
     * @param productId
     * @return java.util.List<cn.itsource.pinggou.domain.Specification>
     */
    List<Specification> loadViewProperties(Long productId);

    /**
     * @author zb
     * @description 保存显示属性
     * @date 2019/5/21
     * @name saveViewProperties
     * @param product
     * @return void
     */
    void saveViewProperties(Product product);
}
