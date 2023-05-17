package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.mapper.leftAndRight.AllLevelCountsMapper;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
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

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    @RedisAnnotation({"firstLevelCounts"})
    public List<AllLevelCounts> getFirstLevelCounts() {
        return allLevelCountsMapper.getFirstLevelCounts();
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    @RedisAnnotation({"secondLevelCounts?levelNo="})
    public List<AllLevelCounts> getSecondLevelCounts(Integer levelNo) {
        return allLevelCountsMapper.getSecondLevelCounts(levelNo);
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @Override
    @RedisAnnotation({"thirdLevelCounts?levelName="})
    public List<AllLevelCounts> getThirdLevelCounts(String levelName) {
        return allLevelCountsMapper.getThirdLevelCounts(levelName);
    }
}