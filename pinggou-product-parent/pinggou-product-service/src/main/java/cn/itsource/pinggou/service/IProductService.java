package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
