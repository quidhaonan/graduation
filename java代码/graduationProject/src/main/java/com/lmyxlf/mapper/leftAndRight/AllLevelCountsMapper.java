package com.lmyxlf.mapper.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每种农产品分类下的农产品数量分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface AllLevelCountsMapper {
    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    List<AllLevelCounts> getFirstLevelCounts();

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    List<AllLevelCounts> getSecondLevelCounts(@Param("levelNo") Integer levelNo);

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    List<AllLevelCounts> getThirdLevelCounts(@Param("levelName") String levelName);
}
