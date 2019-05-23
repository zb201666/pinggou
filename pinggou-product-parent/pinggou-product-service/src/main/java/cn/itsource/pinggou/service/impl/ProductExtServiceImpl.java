package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.ProductExt;
import cn.itsource.pinggou.mapper.ProductExtMapper;
import cn.itsource.pinggou.service.IProductExtService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品扩展 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
@Service
public class ProductExtServiceImpl extends ServiceImpl<ProductExtMapper, ProductExt> implements IProductExtService {

    @Override
    public ProductExt loadProductExtByProductId(Long productId) {
        return baseMapper.selectOne(new QueryWrapper<ProductExt>().eq("productId", productId));
    }
}
