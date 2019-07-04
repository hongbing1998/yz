package org.edu.cdtu.yz.query;

import lombok.Data;

/**
 * @author wencheng
 * @since 2019-06-25
 */
@Data
public class PageQuery {
    /**
     * 当前页码（从1开始）
     */
    private Integer page;
    /**
     * 每页大小
     */
    private Integer rows;

    public PageQuery() {
    }

    public PageQuery(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }


    public Integer getStart() {
        return (page - 1) * rows;
    }
}