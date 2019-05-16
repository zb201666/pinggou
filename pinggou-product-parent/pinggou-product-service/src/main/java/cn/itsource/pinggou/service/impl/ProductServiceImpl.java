package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.mapper.ProductMapper;
import cn.itsource.pinggou.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
