package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.ProductType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
