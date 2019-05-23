package cn.itsource.es;

import cn.itsource.pinggou.CommonApplication;
import cn.itsource.pinggou.domain.ProductDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zb
 * @version 1.0
 * @classname EsTest
 * @description TODO
 * @date 2019/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonApplication.class)
public class EsTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void test(){
        elasticsearchTemplate.deleteIndex("pinggou");
        elasticsearchTemplate.createIndex("pinggou");
        elasticsearchTemplate.putMapping(ProductDoc.class);
    }
}
