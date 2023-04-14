package com.lmyxlf.entity.leftAndRight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 每种农产品分类下的农产品数量分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AllLevelCounts {
    /**
     * 等级编号
     */
    private Integer levelNo;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * 总数量
     */
    private Integer counts;
    /**
     * 所占百分比
     */
    private Double percent;
}
