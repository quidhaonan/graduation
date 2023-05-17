package com.lmyxlf.mapper.middle;

import com.lmyxlf.entity.middle.TotalCounts;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 获取农产品与商铺的总数量的映射类
 *
 * @author lmy
 */
@Mapper
public interface TotalCountsMapper {
    /**
     * 获取农产品与商铺的总数量
     *
     * @return 符合要求的 TotalCounts 对象集合
     */
    List<TotalCounts> getTotalCounts();
}