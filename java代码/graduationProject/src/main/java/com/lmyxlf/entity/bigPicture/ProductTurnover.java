package com.lmyxlf.entity.bigPicture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;


/**
 * 农产品总营业额（存储农产品表所有信息）
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductTurnover {
    /**
     * ID
     */
    private Integer id;
    /**
     * 外键，第三级分类名称，third_level 表的主键
     */
    private Integer thirdLevel;
    /**
     * 名称
     */
    private String proName;
    /**
     * 价格
     */
    private Double price;
    /**
     * 营业额
     */
    private Double turnover;
    /**
     * 起批量
     */
    private Integer startBatching;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 外键，发货地址，province 表的主键
     */
    private Integer shipFromAddress;
    /**
     * 详细发货地址
     */
    private String address;
    /**
     * 采购热度
     */
    private Integer purchasingHeat;
    /**
     * 收藏人数
     */
    private Integer collectorsCounts;
    /**
     * 询价人数
     */
    private Integer inquiry;
    /**
     * 成交人数
     */
    private Integer traded;
    /**
     * 评价人数
     */
    private Integer assess;
    /**
     * 描述介绍
     */
    private String proDesc;
    /**
     * 农产品 URL
     */
    private String proUrl;
    /**
     * 商铺 URL
     */
    private String shopUrl;
}