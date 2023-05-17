package com.lmyxlf.entity.middle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 每个省市农产品的数量分析
 *
 * @author lmy
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class MapOfChina {
    /**
     * 省市 ID
     */
    private Integer provinceId;
    /**
     * 省市名称
     */
    private String provinceName;
    /**
     * 省市农产品数量
     */
    private Integer counts;
}