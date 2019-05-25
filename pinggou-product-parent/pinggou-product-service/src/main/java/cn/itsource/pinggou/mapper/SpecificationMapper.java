package cn.itsource.pinggou.mapper;

import cn.itsource.pinggou.domain.Specification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
public interface SpecificationMapper extends BaseMapper<Specification> {
    /**
     * @author zb
     * @description 根据商品id查询对应的商品属性
     * @date 2019/5/25
     * @name selectSpecificationsByProductTypeId
     * @param productTypeId
     * @return java.util.List<cn.itsource.pinggou.domain.Specification>
     */
    List<Specification> selectSpecificationsByProductTypeId(Long productTypeId);
}
