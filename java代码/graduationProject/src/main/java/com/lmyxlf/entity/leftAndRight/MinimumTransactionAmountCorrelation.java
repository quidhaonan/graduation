package com.lmyxlf.entity.leftAndRight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 最低交易金额相关性分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MinimumTransactionAmountCorrelation {
    /**
     * ID
     */
    private Integer id;
    /**
     * 最低交易金额（分组后）
     */
    private Double minimumTransactionAmountCorrelation;
    /**
     * 收藏人数（分组后）
     */
    private Double collectorsCounts;
    /**
     * 询价人数（分组后）
     */
    private Double inquiry;
    /**
     * 成交人数（分组后）
     */
    private Double traded;
    /**
     * 评价人数（分组后）
     */
    private Double assess;
    /**
     * 处于该分组的农产品数
     */
    private Integer counts;
}