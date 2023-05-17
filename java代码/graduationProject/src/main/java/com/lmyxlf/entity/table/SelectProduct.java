package com.lmyxlf.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 农产品详细信息分析的查询条件
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SelectProduct {
    /**
     * 搜索输入值
     */
    private String input;
    /**
     * 价格排序
     */
    private String price;
    /**
     * 收藏人数排序
     */
    private String collectorsCounts;
    /**
     * 营业额排序
     */
    private String turnover;
    /**
     * 收藏地址过滤
     */
    private Integer[] shipFromAddress;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页农产品数
     */
    private Integer pageSize;
}