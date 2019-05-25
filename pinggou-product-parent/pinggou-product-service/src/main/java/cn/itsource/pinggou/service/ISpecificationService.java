package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
public interface ISpecificationService extends IService<Specification> {

    /**
     * @author zb
     * @description 根据商品id查询对应的商品属性
     * @date 2019/5/25
     * @name loadSpecificationsByProductTypeId
     * @param productTypeId
     * @return
     */
    List<Specification> loadSpecificationsByProductTypeId(Long productTypeId);
}
