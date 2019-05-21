package cn.itsource.pinggou.mapper;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author zb
 * @since 2019-05-20
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * @author zb
     * @description 带有自定义高级查询的分页查询
     * @date 2019/5/20
     * @name selectByQuery
     * @param page
     * @param query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<cn.itsource.pinggou.domain.Product>
     */
    IPage<Product> selectByQuery(Page<Product> page, @Param("query") ProductQuery query);
}
