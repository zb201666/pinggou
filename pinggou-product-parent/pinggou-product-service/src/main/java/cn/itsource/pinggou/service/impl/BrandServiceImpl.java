package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Brand;
import cn.itsource.pinggou.mapper.BrandMapper;
import cn.itsource.pinggou.query.BrandQuery;
import cn.itsource.pinggou.service.IBrandService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public IPage<Brand> selectByQuery(Page<Brand> page, BrandQuery query) {
        return baseMapper.selectByQuery(page,query);
    }

    @Override
    public void updateLogo(Long id, String logo) {
        baseMapper.updateLogo(id,logo);
    }
}
