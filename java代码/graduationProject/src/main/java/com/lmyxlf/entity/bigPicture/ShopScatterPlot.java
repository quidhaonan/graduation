package com.lmyxlf.entity.bigPicture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商铺散点图分析（共存储 12 个字段，其中包含商铺表能数字化比较的 10 个字段）
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ShopScatterPlot implements Serializable {
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
     * 商铺热度
     */
    private Integer shopHeat;
    /**
     * 商铺等级
     */
    private Integer shopGrade;
    /**
     * 累计营业额
     */
    private Double cumulativeTurnover;
    /**
     * 粉丝数
     */
    private Integer fansCounts;
    /**
     * 平均发货速度
     */
    private Double averageShippingSpeed;
    /**
     * 售后率
     */
    private Double afterSalesRate;
    /**
     * 复购率
     */
    private Double repurchaseRate;
    /**
     * 评分
     */
    private Double shopRatings;
}
