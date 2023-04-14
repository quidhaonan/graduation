package com.lmyxlf.service.bigPicture.impl;

import com.lmyxlf.entity.bigPicture.ShopScatterPlot;
import com.lmyxlf.mapper.bigPicture.ShopScatterPlotMapper;
import com.lmyxlf.service.bigPicture.ShopScatterPlotService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商铺散点图的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class ShopScatterPlotServiceImpl implements ShopScatterPlotService {
    @Resource
    private ShopScatterPlotMapper shopScatterPlotMapper;
    @Resource
    private RedisTemplate<String,List<ShopScatterPlot>> redisTemplate;

    /**
     *获得商铺表的所有商铺（共 12 个字段，其中包含商铺表能数字化比较的 10 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    @Override
    public List<ShopScatterPlot> getShopScatterPlot() {
        // 查 redis 缓存
        List<ShopScatterPlot> cache = redisTemplate.opsForValue().get("shopScatterPlot");
        if(cache!=null){
            System.out.println("ShopScatterPlotServiceImpl-->走了缓存");
            return cache;
        }

        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotMapper.getShopScatterPlot();
        System.out.println("ShopScatterPlotServiceImpl-->没走缓存");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("shopScatterPlot",shopScatterPlot);
        return shopScatterPlot;
    }
}
