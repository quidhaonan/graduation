package com.lmyxlf.mapper.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelTurnover;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 每种农产品分类下的农产品营业额分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface AllLevelTurnoverMapper {

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    List<AllLevelTurnover> getFirstLevelTurnover();

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    List<AllLevelTurnover> getSecondLevelTurnover(@Param("levelNo") Integer levelNo);

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    List<AllLevelTurnover> getThirdLevelTurnover(@Param("levelName") String levelName);
}
