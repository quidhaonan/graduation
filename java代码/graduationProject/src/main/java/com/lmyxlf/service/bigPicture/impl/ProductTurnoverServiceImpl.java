package com.lmyxlf.service.bigPicture.impl;

import com.lmyxlf.entity.bigPicture.ProductTurnover;
import com.lmyxlf.mapper.bigPicture.ProductTurnoverMapper;
import com.lmyxlf.service.bigPicture.ProductTurnoverService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 农产品总营业额的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class ProductTurnoverServiceImpl implements ProductTurnoverService {
    @Resource
    private ProductTurnoverMapper productTurnoverMapper;
    @Resource
    private RedisTemplate<String,List<ProductTurnover>> redisTemplate;

    /**
     *获得商品销售额大于 0 的所有商品
     *
     * @return 符合要求的 ProductTurnover 对象集合
     */
    @Override
    public List<ProductTurnover> getProductTurnover(){
        // 查 redis 缓存
        List<ProductTurnover> cache = redisTemplate.opsForValue().get("productTurnover");
        if(cache!=null){
            System.out.println("ProductTurnoverServiceImpl-->走了缓存");
            return cache;
        }

        List<ProductTurnover> productTurnover = productTurnoverMapper.getProductTurnover();
        System.out.println("ProductTurnoverServiceImpl-->没走缓存");
        // 添加 redis 缓存
        redisTemplate.opsForValue().set("productTurnover",productTurnover);
        return productTurnover;
    }
}
