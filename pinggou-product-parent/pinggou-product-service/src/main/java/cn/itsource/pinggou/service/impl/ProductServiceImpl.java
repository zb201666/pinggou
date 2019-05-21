package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.domain.ProductExt;
import cn.itsource.pinggou.mapper.ProductExtMapper;
import cn.itsource.pinggou.mapper.ProductMapper;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.service.IProductService;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductExtMapper productExtMapper;

    @Override
    public PageList<Product> selectByQuery(ProductQuery query) {
        Page<Product> page = new Page<Product>(query.getPage(),query.getSize());
        IPage<Product> iPage = baseMapper.selectByQuery(page,query);
        return new PageList<Product>(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public boolean save(Product product) {
        try {
            super.save(product);
            ProductExt productExt = new ProductExt();
            productExt.setRichContent(product.getContent());
            productExt.setDescription(product.getDescription());
            productExt.setProductId(product.getId());
            productExtMapper.insert(productExt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
