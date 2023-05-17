package com.lmyxlf.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SelectShop {
    /**
     * 搜索框输入值
     */
    private String input;
    /**
     * 总营业额排序
     */
    private String cumulativeTurnover;
    /**
     * 粉丝数排序
     */
    private String fansCounts;
    /**
     * 商铺地址过滤
     */
    private Integer[] provinceId;
    /**
     * 是否是 VIP 过滤
     */
    private Integer[] isVip;
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页商铺数
     */
    private Integer pageSize;
}