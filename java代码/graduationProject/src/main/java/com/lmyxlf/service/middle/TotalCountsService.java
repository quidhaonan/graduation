package com.lmyxlf.service.middle;

import com.lmyxlf.entity.middle.TotalCounts;

import java.util.List;

/**
 * 获取农产品与商铺的总数量的服务类接口
 *
 * @author lmy
 */
public interface TotalCountsService {

    /**
     * 获取农产品与商铺的总数量
     *
     * @return 符合要求的 TotalCounts 对象集合
     */
    List<TotalCounts> getTotalCounts();
}
