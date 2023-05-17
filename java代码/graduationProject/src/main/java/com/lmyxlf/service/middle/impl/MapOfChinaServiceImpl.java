package com.lmyxlf.service.middle.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.middle.MapOfChina;
import com.lmyxlf.mapper.middle.MapOfChinaMapper;
import com.lmyxlf.service.middle.MapOfChinaService;
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

    /**
     * 获得每个省市农产品的数量，制作中国地图
     *
     * @return 符合要求的 MapOfChina 对象集合
     */
    @Override
    @RedisAnnotation({"theNumberOfAgriculturalProductsInTheProvinceAndCity"})
    public List<MapOfChina> getTheNumberOfAgriculturalProductsInTheProvinceAndCity() {
        return mapOfChinaMapper.getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
    }
}