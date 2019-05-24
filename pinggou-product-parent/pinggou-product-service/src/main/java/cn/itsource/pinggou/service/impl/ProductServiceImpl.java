package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.client.ElasticSearchClient;
import cn.itsource.pinggou.domain.*;
import cn.itsource.pinggou.mapper.*;
import cn.itsource.pinggou.query.ProductQuery;
import cn.itsource.pinggou.service.IProductService;
import cn.itsource.pinggou.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

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

    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private BrandMapper brandMapper;


    /**
     * @author zb
     * @description 重写删除，删除后要同步到es
     * @date 2019/5/24
     * @name removeById
     * @param id
     * @return boolean
     */
    @Override
    @Transactional
    public boolean removeById(Serializable id) {
        Product product = baseMapper.selectById(id);
        super.removeById(id);
        if(product.getState() == 1){ // 上架才删除
            elasticSearchClient.remove(product.getId());
        }
        return true;
    }

    /**
     * @author zb
     * @description 重写更新，更新后要同步到es
     * @date 2019/5/24
     * @name updateById
     * @param entity
     * @return boolean
     */
    @Override
    @Transactional
    public boolean updateById(Product entity) {
        super.updateById(entity);
        Product product = baseMapper.selectById(entity.getId());
        if(product.getState() == 1){ // 上架的进行同步
            elasticSearchClient.save(productsToProductDoc(product));
        }
        return true;
    }

    /**
     * @author zb
     * @description 重写批量删除
     * @date 2019/5/24
     * @name removeByIds
     * @param idList
     * @return boolean
     */
    @Override
    @Transactional
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        List<Product> products = baseMapper.selectBatchIds(idList);
        super.removeByIds(idList);
        products.forEach(p->{
            if(p.getState() == 1){
                elasticSearchClient.remove(p.getId());
            }
        });
        return true;
    }

    @Override
    @Transactional
    public void saveSkuProperties(Map<String, Object> params) {
        Long productId = ((Integer) params.get("productId")).longValue();
        Product selectProduct = baseMapper.selectById(productId);
        selectProduct.setSkuProperties((String) params.get("skuProperties"));
        baseMapper.updateById(selectProduct);
        //将sku属性保存到sku表[先删除原有的，然后再添加]
        skuMapper.delete(new QueryWrapper<Sku>().eq("productId", productId));
        List<Map<String, String>> skus = (List<Map<String, String>>) params.get("skus");
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
                if (key.equals("price") || key.equals("skuIndex") || key.equals("availableStock")) {
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
        if (StringUtils.isEmpty(skuProperties)) {
            List<Specification> specifications = specificationMapper.selectList(new QueryWrapper<Specification>().eq("productTypeId", product.getProductTypeId()).eq("isSku", 1));
            return specifications;
        } else {
            List<Specification> specifications = JSONArray.parseArray(skuProperties, Specification.class);
            return specifications;
        }
    }

    @Override
    public List<Sku> loadSkus(Long productId) {
        return skuMapper.selectList(new QueryWrapper<Sku>().eq("productId", productId));
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
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

    @Override
    @Transactional
    public void onSale(List<Long> ids) {
        //更新数据库的上架时间和状态
        baseMapper.onSale(ids, new Date().getTime());
        List<Product> products = baseMapper.selectBatchIds(ids);
        List<ProductDoc> productDocs = productsToProductDocs(products);
        //保存到es
        elasticSearchClient.saveBatch(productDocs);
    }

    @Override
    @Transactional
    public void offSale(List<Long> ids) {
        //更新数据库的下架时间和状态
        baseMapper.offSale(ids, new Date().getTime());
        //批量删除
        elasticSearchClient.removeBatchByIds(ids);
    }

    private List<ProductDoc> productsToProductDocs(List<Product> products) {
        List<ProductDoc> productDocs = new ArrayList<>();
        products.forEach(p -> {
            productDocs.add(productsToProductDoc(p));
        });
        return productDocs;
    }

    /**
     * @param product
     * @return cn.itsource.pinggou.domain.ProductDoc
     * @author zb
     * @description product转换为productdoc
     * @date 2019/5/23
     * @name productsToProductDoc
     */
    private ProductDoc productsToProductDoc(Product product) {
        ProductDoc productDoc = new ProductDoc();
        //复制属性值
        BeanUtils.copyProperties(product, productDoc);
        productDoc.setOnSaleTime(product.getOnSaleTime());

        //设置最大值和最小值
        List<Sku> skus = skuMapper.selectList(new QueryWrapper<Sku>().eq("productId", product.getId()));
        Integer minPrice = (skus != null && skus.size() > 0) ? skus.get(0).getPrice() : 0;
        Integer maxPrice = (skus != null && skus.size() > 0) ? skus.get(0).getPrice() : 0;
        for (Sku sku : skus) {
            minPrice = Math.min(sku.getPrice(), minPrice);
            maxPrice = Math.max(sku.getPrice(), maxPrice);
        }
        productDoc.setMinPrice(minPrice);
        productDoc.setMaxPrice(maxPrice);

        //设置all关键字
        ProductType productType = productTypeMapper.selectById(product.getProductTypeId());
        Brand brand = brandMapper.selectById(product.getBrandId());
        StringBuffer sb = new StringBuffer();
        sb.append(product.getName())
                .append(" ")
                .append(product.getSubName())
                .append(" ");
        if (productType != null) {
            sb.append(productType.getName()).append(" ");
        }
        if (brand != null) {
            sb.append(brand.getName());
        }
        productDoc.setAll(sb.toString());
        return productDoc;
    }
}
