package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.domain.Sku;
import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    /**
     * @author zb
     * @description 取得商品的sku属性
     * @date 2019/5/22
     * @name loadSkuProperties
     * @param productId
     * @return java.util.List<cn.itsource.pinggou.domain.Specification>
     */
    List<Specification> loadSkuProperties(Long productId);

    /**
     * @author zb
     * @description 保存商品sku属性
     * @date 2019/5/22
     * @name saveSkuProperties
     * @param params
     * @return void
     */
    void saveSkuProperties(Map<String, Object> params);

    /**
     * @author zb
     * @description 取得商品对应的sku
     * @date 2019/5/22
     * @name loadSkus
     * @param productId
     * @return java.util.List<cn.itsource.pinggou.domain.Sku>
     */
    List<Sku> loadSkus(Long productId);

    /**
     * @author zb
     * @description 商品上架
     * @date 2019/5/23
     * @name onSale
     * @param ids
     * @return void
     */
    void onSale(List<Long> ids);

    /**
     * @author zb
     * @description 商品下架
     * @date 2019/5/24
     * @name offSale
     * @param ids
     * @return void
     */
    void offSale(List<Long> ids);
}
