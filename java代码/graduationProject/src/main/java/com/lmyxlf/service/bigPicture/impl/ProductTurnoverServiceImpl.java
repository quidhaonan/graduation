package com.lmyxlf.service.bigPicture.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.bigPicture.ProductTurnover;
import com.lmyxlf.mapper.bigPicture.ProductTurnoverMapper;
import com.lmyxlf.service.bigPicture.ProductTurnoverService;
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

    /**
     *获得商品销售额大于 0 的所有商品
     *
     * @return 符合要求的 ProductTurnover 对象集合
     */
    @Override
    @RedisAnnotation({"productTurnover"})
    public List<ProductTurnover> getProductTurnover(){
        return productTurnoverMapper.getProductTurnover();
    }
}