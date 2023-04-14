package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.entity.leftAndRight.AllLevelTurnover;
import com.lmyxlf.mapper.leftAndRight.AllLevelTurnoverMapper;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
import com.lmyxlf.service.leftAndRight.AllLevelTurnoverService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每种农产品分类下的农产品营业额分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class AllLevelTurnoverServiceImpl implements AllLevelTurnoverService {
    @Resource
    private AllLevelTurnoverMapper allLevelTurnoverMapper;
    @Resource
    private RedisTemplate<String,List<AllLevelTurnover>> redisTemplate;

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @Override
    public List<AllLevelTurnover> getFirstLevelTurnover() {
        // 查 redis 缓存
        List<AllLevelTurnover> cache = redisTemplate.opsForValue().get("firstLevelTurnover");
        if (cache!=null){
            System.out.println("AllLevelTurnoverServiceImpl-getFirstLevelTurnover-->走了缓存-->YES");
            return cache;
        }

        List<AllLevelTurnover> firstLevelTurnover = allLevelTurnoverMapper.getFirstLevelTurnover();
        System.out.println("AllLevelTurnoverServiceImpl-getFirstLevelTurnover-->没走缓存-->No");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("firstLevelTurnover",firstLevelTurnover);
        return firstLevelTurnover;
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @Override
    public List<AllLevelTurnover> getSecondLevelTurnover(Integer levelNo) {
        // 查 redis 缓存
        List<AllLevelTurnover> cache = redisTemplate.opsForValue().get("secondLevelTurnover?levelNo="+levelNo);
        if (cache!=null){
            System.out.println("AllLevelTurnoverServiceImpl-getSecondLevelTurnover-->走了缓存-->YES");
            return cache;
        }

        List<AllLevelTurnover> secondLevelTurnover = allLevelTurnoverMapper.getSecondLevelTurnover(levelNo);
        System.out.println("AllLevelTurnoverServiceImpl-getSecondLevelTurnover-->没走缓存-->No");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("secondLevelTurnover?levelNo="+levelNo,secondLevelTurnover);
        return secondLevelTurnover;
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @Override
    public List<AllLevelTurnover> getThirdLevelTurnover(String levelName) {
        // 查 redis 缓存
        List<AllLevelTurnover> cache = redisTemplate.opsForValue().get("thirdLevelTurnover?levelName="+levelName);
        if (cache!=null){
            System.out.println("AllLevelTurnoverServiceImpl-getThirdLevelTurnover-->走了缓存-->YES");
            return cache;
        }

        List<AllLevelTurnover> thirdLevelTurnover = allLevelTurnoverMapper.getThirdLevelTurnover(levelName);
        System.out.println("AllLevelTurnoverServiceImpl-getThirdLevelTurnover-->没走缓存-->No");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("thirdLevelTurnover?levelName="+levelName,thirdLevelTurnover);
        return thirdLevelTurnover;
    }
}
