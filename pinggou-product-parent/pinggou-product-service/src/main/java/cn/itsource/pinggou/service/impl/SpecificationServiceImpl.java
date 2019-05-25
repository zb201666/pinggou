package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Specification;
import cn.itsource.pinggou.mapper.SpecificationMapper;
import cn.itsource.pinggou.service.ISpecificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public List<Specification> loadSpecificationsByProductTypeId(Long productTypeId) {
        return specificationMapper.selectSpecificationsByProductTypeId(productTypeId);
    }
}
