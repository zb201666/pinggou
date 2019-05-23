package cn.itsource.pinggou.repository;

import cn.itsource.pinggou.domain.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zb
 * @version 1.0
 * @classname ProductDocRepository
 * @description TODO
 * @date 2019/5/23
 */
@Repository
public interface ProductDocRepository extends ElasticsearchRepository<ProductDoc,Long> {
}
