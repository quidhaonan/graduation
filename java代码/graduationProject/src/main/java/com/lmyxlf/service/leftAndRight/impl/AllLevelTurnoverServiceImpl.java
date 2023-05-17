package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.leftAndRight.AllLevelTurnover;
import com.lmyxlf.mapper.leftAndRight.AllLevelTurnoverMapper;
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
    @RedisAnnotation({"firstLevelTurnover"})
    public List<AllLevelTurnover> getFirstLevelTurnover() {
        return allLevelTurnoverMapper.getFirstLevelTurnover();
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @Override
    @RedisAnnotation({"secondLevelTurnover?levelNo="})
    public List<AllLevelTurnover> getSecondLevelTurnover(Integer levelNo) {
        return allLevelTurnoverMapper.getSecondLevelTurnover(levelNo);
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @Override
    @RedisAnnotation({"thirdLevelTurnover?levelName="})
    public List<AllLevelTurnover> getThirdLevelTurnover(String levelName) {
        return allLevelTurnoverMapper.getThirdLevelTurnover(levelName);
    }
}