package com.lmyxlf.mapper.middle;

import com.lmyxlf.entity.middle.MapOfChina;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 每个省市农产品的数量分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface MapOfChinaMapper {

    /**
     * 获得每个省市农产品的数量，制作中国地图
     *
     * @return 符合要求的 MapOfChina 对象集合
     */
    List<MapOfChina> getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
}
