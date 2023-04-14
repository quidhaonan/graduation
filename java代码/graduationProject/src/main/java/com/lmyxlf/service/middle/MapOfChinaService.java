package com.lmyxlf.service.middle;

import com.lmyxlf.entity.middle.MapOfChina;

import java.util.List;

/**
 * 每个省市农产品的数量分析的服务类接口
 *
 * @author lmy
 */
public interface MapOfChinaService {

    /**
     * 获得每个省市农产品的数量，制作中国地图
     *
     * @return 符合要求的 MapOfChina 对象集合
     */
    List<MapOfChina> getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
}
