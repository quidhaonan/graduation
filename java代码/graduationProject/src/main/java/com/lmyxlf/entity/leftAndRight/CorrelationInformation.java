package com.lmyxlf.entity.leftAndRight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 为 最低交易金额相关性 获得的临时信息，用于页面显示，存储在本次合并计算中所占比例较大的农产品信息
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CorrelationInformation {
    /**
     * 名称
     */
    private String name;
    /**
     * 数值
     */
    private Double value;
}