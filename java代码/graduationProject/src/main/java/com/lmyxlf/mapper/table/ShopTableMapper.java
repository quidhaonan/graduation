package com.lmyxlf.mapper.table;

import com.lmyxlf.entity.table.SelectShop;
import com.lmyxlf.entity.table.ShopTable;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * 商铺详细信息分析的映射类
 *
 * @author lmy
 */
@Mapper
public interface ShopTableMapper {
    /**
     * 获取商铺详细信息
     *
     * @param selectShop 查询条件
     * @return 符合要求的 ShopTable 对象集合
     */
    List<ShopTable> getShopTableInformation(SelectShop selectShop);

    /**
     * 获得符合要求的商铺个数
     *
     * @param selectShop 查询条件
     * @return 符合要求的商铺个数
     */
    Integer getTotalCounts(SelectShop selectShop);
}