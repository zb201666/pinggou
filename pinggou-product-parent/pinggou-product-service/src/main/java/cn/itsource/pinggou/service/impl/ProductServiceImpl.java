package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.domain.ProductExt;
import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.mapper.ProductExtMapper;
import cn.itsource.pinggou.mapper.ProductMapper;
import cn.itsource.pinggou.mapper.ProductTypeMapper;
import cn.itsource.pinggou.mapper.SpecificationMapper;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.service.IProductService;
import cn.itsource.pinggou.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public void saveViewProperties(Product product) {
        Product selectProduct = baseMapper.selectById(product.getId());
        selectProduct.setViewProperties(product.getViewProperties());
        baseMapper.updateById(selectProduct);
    }

    @Override
    public List<Specification> loadViewProperties(Long productId) {
        Product product = baseMapper.selectById(productId);
        String viewProperties = product.getViewProperties();
        //判断商品的显示属性是否存在
        if(StringUtils.isEmpty(viewProperties)){//不存在就去t_specification表中查询
            return specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", product.getProductTypeId()));
        }
        //存在就将保存的数据解析后返回
        return JSONArray.parseArray(viewProperties, Specification.class);
    }

    @Override
    public void updateMedias(Long id, String medias) {
        baseMapper.updateMedias(id,medias);
    }

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
