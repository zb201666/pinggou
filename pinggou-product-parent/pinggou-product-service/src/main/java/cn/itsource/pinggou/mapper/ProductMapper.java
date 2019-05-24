package cn.itsource.pinggou.mapper;

import cn.itsource.pinggou.domain.Product;
import cn.itsource.pinggou.query.ProductQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * @author zb
     * @description 根据id修改媒体信息
     * @date 2019/5/21
     * @name updateMedias
     * @param id
     * @param medias
     * @return void
     */
    @Update("update t_product set medias = #{medias} where id = #{id}")
    void updateMedias(@Param("id") Long id, @Param("medias") String medias);

    /**
     * @author zb
     * @description 商品上架
     * @date 2019/5/23
     * @name onSale
     * @param ids
     * @param onSaleTime
     * @return void
     */
    void onSale(@Param("ids") List<Long> ids, @Param("onSaleTime") Long onSaleTime);

    /**
     * @author zb
     * @description 商品下架
     * @date 2019/5/24
     * @name offSale
     * @param ids
     * @param offSaleTime
     * @return void
     */
    void offSale(@Param("ids") List<Long> ids, @Param("offSaleTime") Long offSaleTime);
}
