package com.lmyxlf.mapper.table;

import com.lmyxlf.entity.table.ProductTable;
import com.lmyxlf.entity.table.SelectProduct;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 农产品详细信息分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface ProductTableMapper {
    /**
     * 获取农产品详细信息
     *
     * @param selectProduct 查询条件
     * @return 符合要求的 ProductTable 对象集合
     */
    List<ProductTable> getProductTableInformation(SelectProduct selectProduct);

    /**
     * 获得符合要求的农产品个数
     *
     * @param selectProduct 查询条件
     * @return 符合要求的农产品个数
     */
    Integer getTotalCounts(SelectProduct selectProduct);
}