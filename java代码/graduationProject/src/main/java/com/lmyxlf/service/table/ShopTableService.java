package com.lmyxlf.service.table;

import com.lmyxlf.entity.table.SelectShop;
import com.lmyxlf.entity.table.ShopTable;
import java.util.List;


/**
 * 商铺详细信息分析的服务类接口
 *
 * @author lmy
 */
public interface ShopTableService {
    /**
     * 获取商铺详细信息
     *
     * @param selectShop 查询条件
     * @return 符合要求的 ShopTable 对象集合
     */
    List<ShopTable> getShopTableInformation(SelectShop selectShop);
}