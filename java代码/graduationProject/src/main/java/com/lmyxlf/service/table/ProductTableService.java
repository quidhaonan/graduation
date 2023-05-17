package com.lmyxlf.service.table;

import com.lmyxlf.entity.table.ProductTable;
import com.lmyxlf.entity.table.SelectProduct;
import java.util.List;


/**
 * 农产品详细信息分析的服务类接口
 *
 * @author lmy
 */
public interface ProductTableService {
    /**
     * 获取农产品详细信息
     *
     * @param selectProduct 查询条件
     * @return 符合要求的 ProductTable 对象集合
     */
    List<ProductTable> getProductTableInformation(SelectProduct selectProduct);
}