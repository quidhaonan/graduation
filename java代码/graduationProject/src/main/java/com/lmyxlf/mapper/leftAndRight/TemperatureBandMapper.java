package com.lmyxlf.mapper.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * 温度带分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface TemperatureBandMapper {
    /**
     * 获取各个温度带的出售农产品数量
     *
     * @param provinceIds 每个温度带所包含的省份 ID
     * @return 符合要求的 AllLevelCounts 对象集合（因返回字段和该实体类属性相同，因此使用该实体类）
     */
    List<AllLevelCounts> getTheNumberOfProductsInEachTemperatureZone(@Param("provinceIds") String[] provinceIds);
}