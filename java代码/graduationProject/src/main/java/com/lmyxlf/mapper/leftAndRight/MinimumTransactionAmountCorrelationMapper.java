package com.lmyxlf.mapper.leftAndRight;

import com.lmyxlf.entity.leftAndRight.MinimumTransactionAmountCorrelation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 最低交易金额相关性分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface MinimumTransactionAmountCorrelationMapper {
    /**
     * 获取每个最低交易额等级的详细信息（包含收藏人数、询价人数、成交人数、评价人数、处于该等级下的农产品总数）
     *
     * @return 符合要求的 MinimumTransactionAmountCorrelation 对象集合
     */
    List<MinimumTransactionAmountCorrelation> getMinimumTransactionAmountCorrelation();
}