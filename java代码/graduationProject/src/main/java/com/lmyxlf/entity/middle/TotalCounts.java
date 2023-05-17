package com.lmyxlf.entity.middle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 获取农产品与商铺的总数量
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class TotalCounts {
    /**
     * ID
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 总数量
     */
    private Integer totalCounts;
}