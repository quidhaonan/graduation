package com.lmyxlf.service.middle.impl;

import com.lmyxlf.entity.middle.TotalCounts;
import com.lmyxlf.mapper.middle.TotalCountsMapper;
import com.lmyxlf.service.middle.TotalCountsService;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Resource
    private RedisTemplate<String,List<TotalCounts>> redisTemplate;

    /**
     * 获取农产品与商铺的总数量
     *
     * @return 符合要求的 TotalCounts 对象集合
     */
    @Override
    public List<TotalCounts> getTotalCounts() {
        // 查 redis 缓存
        List<TotalCounts> cache = redisTemplate.opsForValue().get("totalCounts");
        if(cache!=null){
            System.out.println("MapOfChinaServiceImpl-getTotalCounts-->走了缓存-->YES");
            return cache;
        }

        List<TotalCounts> totalCounts = totalCountsMapper.getTotalCounts();
        System.out.println("MapOfChinaServiceImpl-getTotalCounts-->没走缓存-->NO");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("totalCounts",totalCounts);
        return totalCounts;
    }
}
