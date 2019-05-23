package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.ProductExt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品扩展 服务类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
public interface IProductExtService extends IService<ProductExt> {

    /**
     * @author zb
     * @description 根据商品id查询详情
     * @date 2019/5/23
     * @name loadProductExtByProductId
     * @param productId
     * @return cn.itsource.pinggou.domain.ProductExt
     */
    ProductExt loadProductExtByProductId(Long productId);
}
