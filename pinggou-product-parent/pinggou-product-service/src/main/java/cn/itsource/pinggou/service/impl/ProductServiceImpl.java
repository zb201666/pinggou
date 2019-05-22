package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.*;
import cn.itsource.pinggou.mapper.ProductExtMapper;
import cn.itsource.pinggou.mapper.ProductMapper;
import cn.itsource.pinggou.mapper.SkuMapper;
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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SkuMapper skuMapper;

    @Override
    public void saveSkuProperties(Map<String, Object> params) {
        Long productId = ((Integer)params.get("productId")).longValue();
        Product selectProduct = baseMapper.selectById(productId);
        selectProduct.setSkuProperties((String)params.get("skuProperties"));
        baseMapper.updateById(selectProduct);
        //将sku属性保存到sku表[先删除原有的，然后再添加]
        skuMapper.delete(new QueryWrapper<Sku>().eq("productId", productId));
        List<Map<String,String>> skus = (List<Map<String, String>>) params.get("skus");
        for (Map<String, String> skuMap : skus) {
            Sku sku = new Sku();
            //设置需要保存的sku对象的属性
            sku.setCreateTime(new Date().getTime());
            sku.setUpdateTime(new Date().getTime());
            sku.setProductId(productId);
            sku.setPrice(Integer.valueOf(skuMap.get("price")));
            sku.setAvailableStock(Integer.valueOf(skuMap.get("availableStock")));
            sku.setSkuIndex(skuMap.get("skuIndex"));
            String name = "";
            LinkedHashMap<String, String> skuProperties = new LinkedHashMap<>();
            for (Map.Entry<String, String> entry : skuMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if(key.equals("price")||key.equals("skuIndex")||key.equals("availableStock")){
                    continue;
                }
                name += value;//拼接skuName属性
                skuProperties.put(key, value);//拼接sku_Properties属性
            }
            sku.setSkuName(name);
            sku.setSkuProperties(JSONArray.toJSONString(skuProperties));
            skuMapper.insert(sku);
        }
    }

    @Override
    public List<Specification> loadSkuProperties(Long productId) {
        Product product = baseMapper.selectById(productId);
        String skuProperties = product.getSkuProperties();
        if(StringUtils.isEmpty(skuProperties)){
            List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", product.getProductTypeId()).eq("isSku", 1));
            return specifications;
        }else{
            List<Specification> specifications = JSONArray.parseArray(skuProperties, Specification.class);
            return specifications;
        }
    }

    @Override
    public List<Sku> loadSkus(Long productId) {
        return skuMapper.selectList(new QueryWrapper<Sku>().eq("productId", productId));
    }

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
        if (StringUtils.isEmpty(viewProperties)) {//不存在就去t_specification表中查询
            return specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", product.getProductTypeId()).eq("isSku", 0));
        }
        //存在就将保存的数据解析后返回
        return JSONArray.parseArray(viewProperties, Specification.class);
    }

    @Override
    public void updateMedias(Long id, String medias) {
        baseMapper.updateMedias(id, medias);
    }

    @Override
    public PageList<Product> selectByQuery(ProductQuery query) {
        Page<Product> page = new Page<Product>(query.getPage(), query.getSize());
        IPage<Product> iPage = baseMapper.selectByQuery(page, query);
        return new PageList<Product>(iPage.getTotal(), iPage.getRecords());
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
