package cn.itsource.pinggou.query;

/**
 * @author zb
 * @version 1.0
 * @classname BaseQuery
 * @description TODO
 * @date 2019/5/16
 */
public class BaseQuery {
    /**当前页码*/
    private Integer page = 1;
    /**当前页大小*/
    private Integer size = 10;

    /**关键字查询*/
    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
