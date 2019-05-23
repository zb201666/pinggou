package cn.itsource.pinggou.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zb
 * @version 1.0
 * @classname ProductDoc
 * @description 封装ProductDoc实体类，用于向es中保存数据
 * @date 2019/5/23
 */
@Document(indexName = "pinggou",type="productdoc")
public class ProductDoc {
    /**文档id*/
    @Id
    @Field(type = FieldType.Long,store = true)
    private Long id;

    /**关键字查询字段  [标题  副标题  品牌名称  类型名称]*/
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String all;

    /**商品类型id*/
    @Field(type = FieldType.Long,store = true)
    private Long productTypeId;

    /**商品品牌id*/
    @Field(type = FieldType.Long,store = true)
    private Long brandId;

    /**最低价格*/
    @Field(type = FieldType.Integer,store = true)
    private Integer minPrice;

    /**最高价格*/
    @Field(type = FieldType.Integer,store = true)
    private Integer maxPrice;

    /**销售量*/
    @Field(type = FieldType.Integer,store = true)
    private Integer saleCount;

    /**上线时间*/
    @Field(type = FieldType.Long,store = true)
    private Long onSaleTime;

    /**评论量*/
    @Field(type = FieldType.Integer,store = true)
    private Integer commentCount;

    /**访问量*/
    @Field(type = FieldType.Integer,store = true)
    private Integer viewCount;

    /**标题*/
    @Field(type = FieldType.Keyword,store = true)
    private String name;

    /**副标题*/
    @Field(type = FieldType.Keyword,store = true)
    private String subName;

    /**显示属性*/
    @Field(type = FieldType.Keyword,store = true)
    private String viewProperties;

    /**sku属性*/
    @Field(type = FieldType.Keyword,store = true)
    private String skuProperties;

    /**媒体属性*/
    @Field(type = FieldType.Keyword,store = true)
    private String medias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Long getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Long onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getViewProperties() {
        return viewProperties;
    }

    public void setViewProperties(String viewProperties) {
        this.viewProperties = viewProperties;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public String getMedias() {
        return medias;
    }

    public void setMedias(String medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        return "ProductDoc{" +
                "id=" + id +
                ", all='" + all + '\'' +
                ", productTypeId=" + productTypeId +
                ", brandId=" + brandId +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", saleCount=" + saleCount +
                ", onSaleTime=" + onSaleTime +
                ", commentCount=" + commentCount +
                ", viewCount=" + viewCount +
                ", name='" + name + '\'' +
                ", subName='" + subName + '\'' +
                ", viewProperties='" + viewProperties + '\'' +
                ", skuProperties='" + skuProperties + '\'' +
                ", medias='" + medias + '\'' +
                '}';
    }
}
