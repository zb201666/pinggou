package cn.itsource.pinggou.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zb
 * @version 1.0
 * @classname PageList
 * @description 封装分页工具类
 * @date 2019/5/11
 */
public class PageList<T> {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页数据
     */
    private List<T> rows = new ArrayList<T>();

    public PageList() {}

    public PageList(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
