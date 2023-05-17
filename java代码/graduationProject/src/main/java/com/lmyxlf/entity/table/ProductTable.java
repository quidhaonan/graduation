package com.lmyxlf.entity.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;


/**
 * 农产品详细信息分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ProductTable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 名称
     */
    private String proName;
    /**
     * 价格
     */
    private Double price;
    /**
     * 发货地址编号
     */
    private Integer shipFromAddress;
    /**
     * 详细发货地址
     */
    private String address;
    /**
     * 收藏人数
     */
    private Integer collectorsCounts;
    /**
     * 总营业额
     */
    private Double turnover;
    /**
     * 农产品链接
     */
    private String proUrl;
    /**
     * 商铺链接
     */
    private String shopUrl;
    /**
     * 当前符合查询要求的农产品数量
     */
    private Integer totalCounts;

    /**
     * 以下属性为农产品表第二级显示
     * 外键，第三级分类 ID
     */
    private Integer thirdLevel;
    /**
     * 第三级分类名称
     */
    private String levelName;
    /**
     * 采购热度
     */
    private Integer purchasingHeat;
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
     * 起批量
     */
    private Integer startBatching;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 农产品介绍
     */
    private String proDesc;
}