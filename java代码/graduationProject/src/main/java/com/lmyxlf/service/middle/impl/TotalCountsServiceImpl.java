package com.lmyxlf.service.middle.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.middle.TotalCounts;
import com.lmyxlf.mapper.middle.TotalCountsMapper;
import com.lmyxlf.service.middle.TotalCountsService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 获取农产品与商铺的总数量的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class TotalCountsServiceImpl implements TotalCountsService {
    @Resource
    private TotalCountsMapper totalCountsMapper;

    /**
     * 获取农产品与商铺的总数量
     *
     * @return 符合要求的 TotalCounts 对象集合
     */
    @Override
    @RedisAnnotation({"totalCounts"})
    public List<TotalCounts> getTotalCounts() {
        return totalCountsMapper.getTotalCounts();
    }
}