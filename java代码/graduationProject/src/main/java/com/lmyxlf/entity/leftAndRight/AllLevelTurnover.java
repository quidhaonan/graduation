package com.lmyxlf.entity.leftAndRight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 每种农产品分类下的农产品营业额分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AllLevelTurnover {
    /**
     * 等级编号
     */
    private Integer levelNo;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * 总营业额
     */
    private Double turnover;
    /**
     * 所占百分比
     */
    private Double percent;
}