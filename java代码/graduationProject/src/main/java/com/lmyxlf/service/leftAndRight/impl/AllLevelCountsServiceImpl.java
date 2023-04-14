package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.mapper.leftAndRight.AllLevelCountsMapper;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每种农产品分类下的农产品数量分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class AllLevelCountsServiceImpl implements AllLevelCountsService {
    @Resource
    private AllLevelCountsMapper allLevelCountsMapper;
    @Resource
    private RedisTemplate<String,List<AllLevelCounts>> redisTemplate;

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    public List<AllLevelCounts> getFirstLevelCounts() {
        // 查 redis 缓存
        List<AllLevelCounts> cache = redisTemplate.opsForValue().get("firstLevelCounts");
        if(cache!=null){
            System.out.println("AllLevelCountsServiceImpl-getFirstLevelCounts走了缓存-->YES");
            return cache;
        }

        List<AllLevelCounts> firstLevelCounts = allLevelCountsMapper.getFirstLevelCounts();
        System.out.println("AllLevelCountsServiceImpl-getFirstLevelCounts-->没走缓存-->No");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("firstLevelCounts",firstLevelCounts);
        return firstLevelCounts;
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    public List<AllLevelCounts> getSecondLevelCounts(Integer levelNo) {
        // 查 redis 缓存
        List<AllLevelCounts> cache = redisTemplate.opsForValue().get("secondLevelCounts?levelNo="+levelNo);
        if(cache!=null){
            System.out.println("AllLevelCountsServiceImpl-getSecondLevelCounts-->走了缓存-->YES");
            return cache;
        }

        List<AllLevelCounts> secondLevelCounts = allLevelCountsMapper.getSecondLevelCounts(levelNo);
        System.out.println("AllLevelCountsServiceImpl-getSecondLevelCounts-->没走缓存-->NO");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("secondLevelCounts?levelNo="+levelNo,secondLevelCounts);
        return secondLevelCounts;
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    public List<AllLevelCounts> getThirdLevelCounts(String levelName) {
        // 查 redis 缓存
        List<AllLevelCounts> cache = redisTemplate.opsForValue().get("thirdLevelCounts" + levelName);
        if(cache!=null){
            System.out.println("AllLevelCountsServiceImpl-getThirdLevelCounts-->走了缓存-->Yes");
            return cache;
        }

        List<AllLevelCounts> thirdLevelCounts = allLevelCountsMapper.getThirdLevelCounts(levelName);
        System.out.println("AllLevelCountsServiceImpl-getThirdLevelCounts-->没走缓存-->No");
        // 添加　redis 缓存
        redisTemplate.opsForValue().set("thirdLevelCounts" + levelName,thirdLevelCounts);
        return thirdLevelCounts;
    }
}
