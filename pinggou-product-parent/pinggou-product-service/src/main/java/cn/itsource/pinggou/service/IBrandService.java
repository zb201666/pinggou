package cn.itsource.pinggou.service;

import cn.itsource.pinggou.domain.Brand;
import cn.itsource.pinggou.query.BrandQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
public interface IBrandService extends IService<Brand> {
    /**
     * @author zb
     * @description 带分页的高级查询
     * @date 2019/5/16
     * @name selectByQuery
     * @param page
     * @param query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<cn.itsource.pinggou.domain.Brand>
     */
    IPage<Brand> selectByQuery(Page<Brand> page, BrandQuery query);

    /**
     * @author zb
     * @description 根据id更改logo
     * @date 2019/5/20
     * @name updateLogo
     * @param id
     * @param logo
     * @return void
     */
    void updateLogo(Long id,String logo);
}
