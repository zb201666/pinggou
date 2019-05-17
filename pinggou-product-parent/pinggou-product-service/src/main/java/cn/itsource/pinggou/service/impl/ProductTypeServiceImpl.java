package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.client.RedisClient;
import cn.itsource.pinggou.client.TemplateClient;
import cn.itsource.pinggou.domain.ProductType;
import cn.itsource.pinggou.mapper.ProductTypeMapper;
import cn.itsource.pinggou.service.IProductTypeService;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

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

    private final String KEY = "PRODUCTTYPES_IN_REDIS";


    @Autowired
    private RedisClient redisClient;
    @Autowired
    private TemplateClient templateClient;

    @Override
    public List<ProductType> loadTree() {
        String productTypes = redisClient.get(KEY);
        if (StringUtils.isEmpty(productTypes)){
            //redis里面没有就查询数据库
            List<ProductType> list = loadTreeData();
            String jsonString = JSONArray.toJSONString(list);
            //缓存到redis
            redisClient.set(KEY, jsonString);
            return list;
        }else {
            //redis里面有就直接获取
            List<ProductType> list = JSONArray.parseArray(productTypes, ProductType.class);
            return list;
        }
        //return loadTreeData(0L);
    }


    /**
     * @author zb
     * @description 循环加载树形结构
     * @date 2019/5/16
     * @name loadTreeData
     * @param
     * @return java.util.List<cn.itsource.pinggou.domain.ProductType>
     */
    private List<ProductType> loadTreeData(){
        Map<Long, ProductType> map = new HashMap<Long, ProductType>();
        //查询所有
        List<ProductType> productTypes = baseMapper.selectList(null);
        //将查询的每一个对象存放在map中
        productTypes.forEach(p->{
            map.put(p.getId(), p);
        });

        List<ProductType> list = new ArrayList<ProductType>();
        //再遍历一次，判断当前对象的父级是否存放，如果没有，就表明是顶级父类型，直接加入list
        //如果有，则去map中找到该子类型对应的父类型，同时将该子类型加入父类型的children集合中
        productTypes.forEach(p->{
            if(null!=p.getPid()){
                if(p.getPid()==0){
                    list.add(p);
                }else{
                    map.get(p.getPid()).getChildren().add(p);
                }
            }
        });
        return list;
    }


    /**
     * @author zb
     * @description 递归加载树形结构
     * @date 2019/5/16
     * @name loadTreeData
     * @param id
     * @return java.util.List<cn.itsource.pinggou.domain.ProductType>
     */
    private List<ProductType> loadTreeData(Long id){
        //查询父级id[pid]为传入的id的所有子类型
        List<ProductType> types = baseMapper.selectList(new QueryWrapper<ProductType>().eq("pid", id));
        //没有子类型则停止递归查询
        if(null == types || types.size()==0){
            return types;
        }

        //查询每个子类型的子类型，并将子类型的子类型加入到当前子类型中
        types.forEach(t->{
            List<ProductType> productTypes = loadTreeData(t.getId());
            t.setChildren(productTypes);
        });
        return types;
    }



    @Override
    public boolean save(ProductType entity) {
        boolean save = super.save(entity);
        updateRedisAndPage();
        return save;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean remove = super.removeById(id);
        updateRedisAndPage();
        return remove;
    }

    @Override
    public boolean updateById(ProductType entity) {
        boolean update = super.updateById(entity);
        updateRedisAndPage();
        return update;
    }

    /**
     * @author zb
     * @description 更新redis，更新首页
     * @date 2019/5/17
     * @name updateRedisAndPage
     * @param
     * @return void
     */
    private void updateRedisAndPage(){
        updateRedis();
        generateStaticPage();
    }


    /**
     * @author zb
     * @description 更新redis
     * @date 2019/5/17
     * @name updateRedis
     * @param
     * @return void
     */
    private void updateRedis(){
        List<ProductType> productTypes = loadTreeData();
        String jsonString = JSONArray.toJSONString(productTypes);
        redisClient.set(KEY, jsonString);
    }


    /**
     * @author zb
     * @description 根据模板生成静态首页
     * @date 2019/5/17
     * @name generateStaticPage
     * @param
     * @return void
     */
    @Override
    public void generateStaticPage(){
        Properties properties = null;
        try {
            properties = Resources.getResourceAsProperties("template/staticPage.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //product.type.vm模板路径
        String templatePath = properties.getProperty("productTypeTemplatePath");
        //生成的product.type.vm.html路径
        String targetPath = properties.getProperty("productTypeTargetPath");
        //product.type.vm.html中包含的数据
        List<ProductType> productTypes = loadTreeData();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("model",productTypes);
        params.put("templatePath",templatePath);
        params.put("targetPath",targetPath);
        //根据product.type.vm模板生成 product.type.vm.html
        templateClient.generateStaticPage(params);

        //home.vm模板路径
        templatePath = properties.getProperty("homeTemplatePath");
        //生成的home.html路径
        targetPath = properties.getProperty("homeTargetPath");
        params = new HashMap<String,Object>();
        Map<String,Object> model = new HashMap<String,Object>();
        //静态模板根目录
        model.put("staticRoot",properties.getProperty("staticRootPath"));
        params.put("model",model);
        params.put("templatePath",templatePath);
        params.put("targetPath",targetPath);
        //再根据home.vm生成home.html
        templateClient.generateStaticPage(params);
    }
}
