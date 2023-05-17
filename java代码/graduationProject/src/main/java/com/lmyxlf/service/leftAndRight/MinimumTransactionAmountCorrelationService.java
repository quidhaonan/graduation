package com.lmyxlf.service.leftAndRight;

import java.util.List;


/**
 * 最低交易金额相关性分析的服务类接口
 *
 * @author lmy
 */
public interface MinimumTransactionAmountCorrelationService {
    /**
     * 获取每个最低交易额等级的详细信息（包含收藏人数、询价人数、成交人数、评价人数、处于该等级下的农产品总数）
     *
     * @return 符合要求的 MinimumTransactionAmountCorrelation 对象集合
     */
    List<Object> getMinimumTransactionAmountCorrelation();
}