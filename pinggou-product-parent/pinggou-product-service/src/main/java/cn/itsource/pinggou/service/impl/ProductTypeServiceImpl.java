package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.ProductType;
import cn.itsource.pinggou.mapper.ProductTypeMapper;
import cn.itsource.pinggou.service.IProductTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

}
