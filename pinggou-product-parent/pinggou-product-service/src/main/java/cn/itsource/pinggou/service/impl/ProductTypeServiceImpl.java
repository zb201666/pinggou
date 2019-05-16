package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.ProductType;
import cn.itsource.pinggou.mapper.ProductTypeMapper;
import cn.itsource.pinggou.service.IProductTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<ProductType> loadTree() {
        return loadTreeData();
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
            if(p.getPid()==0){
                list.add(p);
            }else{
                map.get(p.getPid()).getChildren().add(p);
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
}
