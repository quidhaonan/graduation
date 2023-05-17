package com.lmyxlf.service.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import java.util.List;
import java.util.Map;


/**
 * 温度带分析的服务类接口
 *
 * @author lmy
 */
public interface TemperatureBandService {
    /**
     * 获取各个温度带的出售农产品数量
     *
     * @return 符合要求的 AllLevelCounts 对象集合（因返回字段和该实体类属性相同，因此使用该实体类）
     */
    Map<String,List<AllLevelCounts>> getTheNumberOfProductsInEachTemperatureZone();
}