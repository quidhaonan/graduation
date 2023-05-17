package com.lmyxlf.mapper.leftAndRight;

import com.lmyxlf.entity.leftAndRight.CorrelationInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 为 最低交易金额相关性 获得的临时信息，用于页面显示，存储在本次合并计算中所占比例较大的农产品信息
 *
 * @author lmy
 */
@Mapper
public interface CorrelationInformationMapper {
    /**
     * 获取对分组后数据的收藏人数影响最大的临时信息
     *
     * @param start 该分组数据位于总数据的开始位置
     * @param counts 该分组数据所使用的数据条数
     * @return 符合要求的 CorrelationInformation 对象集合
     */
    CorrelationInformation getCollectorsCountsMax(@Param("start") Integer start,@Param("counts") Integer counts);

    /**
     * 获取对分组后数据的询价人数影响最大的临时信息
     *
     * @param start 该分组数据位于总数据的开始位置
     * @param counts 该分组数据所使用的数据条数
     * @return 符合要求的 CorrelationInformation 对象集合
     */
    CorrelationInformation getInquiryMax(@Param("start") Integer start,@Param("counts") Integer counts);

    /**
     * 获取对分组后数据的成交人数影响最大的临时信息
     *
     * @param start 该分组数据位于总数据的开始位置
     * @param counts 该分组数据所使用的数据条数
     * @return 符合要求的 CorrelationInformation 对象集合
     */
    CorrelationInformation getTradedMax(@Param("start") Integer start,@Param("counts") Integer counts);

    /**
     * 获取对分组后数据的成交人数影响最大的临时信息
     *
     * @param start 该分组数据位于总数据的开始位置
     * @param counts 该分组数据所使用的数据条数
     * @return 符合要求的 CorrelationInformation 对象集合
     */
    CorrelationInformation getAssessMax(@Param("start") Integer start,@Param("counts") Integer counts);
}