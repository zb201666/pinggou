package cn.itsource.pinggou.mapper;

import cn.itsource.pinggou.domain.ProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品目录 Mapper 接口
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
public interface ProductTypeMapper extends BaseMapper<ProductType> {

    List<ProductType> selectByPId(Long id);
}
