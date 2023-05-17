package com.lmyxlf.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;


/**
 * 商铺详细信息分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ShopTable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 外键，省份 ID，province 表的主键
     */
    private Integer provinceId;
    /**
     * 是否是 VIP
     */
    private Integer isVip;
    /**
     * 主营
     */
    private String mainBusiness;
    /**
     * 介绍
     */
    private String shopDesc;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 商铺链接
     */
    private String shopUrl;
    /**
     * 商铺热度
     */
    private Integer shopHeat;
    /**
     * 商铺等级
     */
    private Integer shopGrade;
    /**
     * 总营业额
     */
    private Double cumulativeTurnover;
    /**
     * 粉丝数
     */
    private Integer fansCounts;
    /**
     * 平均发货速度
     */
    private String averageShippingSpeed;
    /**
     * 售后率
     */
    private String afterSalesRate;
    /**
     * 复购率
     */
    private String repurchaseRate;
    /**
     * 商铺评分
     */
    private Double shopRatings;
    /**
     * 当前符合查询要求的商铺数量
     */
    private Integer totalCounts;
    /**
     * 该商铺对应下的农产品
     */
    private List<ProductTable> productTable;
}