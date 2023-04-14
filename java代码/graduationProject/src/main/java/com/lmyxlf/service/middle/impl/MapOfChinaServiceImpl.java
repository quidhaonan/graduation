package com.lmyxlf.service.middle.impl;

import com.lmyxlf.entity.middle.MapOfChina;
import com.lmyxlf.mapper.middle.MapOfChinaMapper;
import com.lmyxlf.service.middle.MapOfChinaService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每个省市农产品的数量分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class MapOfChinaServiceImpl implements MapOfChinaService {
    @Resource
    private MapOfChinaMapper mapOfChinaMapper;
    @Resource
    private RedisTemplate<String,List<MapOfChina>> redisTemplate;

    /**
     * 获得每个省市农产品的数量，制作中国地图
     *
     * @return 符合要求的 MapOfChina 对象集合
     */
    @Override
    public List<MapOfChina> getTheNumberOfAgriculturalProductsInTheProvinceAndCity() {
        // 查 redis 缓存
        List<MapOfChina> cache = redisTemplate.opsForValue().get("theNumberOfAgriculturalProductsInTheProvinceAndCity");
        if(cache!=null){
            System.out.println("MapOfChinaServiceImpl-getTheNumberOfAgriculturalProductsInTheProvinceAndCity-->YES");
            return cache;
        }

        List<MapOfChina> theNumberOfAgriculturalProductsInTheProvinceAndCity =
                mapOfChinaMapper.getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
        System.out.println("MapOfChinaServiceImpl-getTheNumberOfAgriculturalProductsInTheProvinceAndCity-->没走缓存-->No");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("theNumberOfAgriculturalProductsInTheProvinceAndCity",
                theNumberOfAgriculturalProductsInTheProvinceAndCity);
        return theNumberOfAgriculturalProductsInTheProvinceAndCity;
    }
}
