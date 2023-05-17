package com.lmyxlf.mapper.bigPicture;

import com.lmyxlf.entity.bigPicture.ProductTurnover;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 农产品总营业额的映射类
 *
 * @author lmy
 */
@Mapper
public interface ProductTurnoverMapper {
    /**
     *获得商品销售额大于 0 的所有商品
     *
     * @return 符合要求的 ProductTurnover 对象集合
     */
    List<ProductTurnover> getProductTurnover();
}