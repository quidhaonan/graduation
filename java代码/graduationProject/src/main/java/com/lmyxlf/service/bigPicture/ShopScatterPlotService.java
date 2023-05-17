package com.lmyxlf.service.bigPicture;

import java.util.List;


/**
 * 商铺散点图的服务类接口
 *
 * @author lmy
 */
public interface ShopScatterPlotService {
    /**
     *获得商铺表的所有商铺（共 11 个字段，其中包含商铺表能数字化比较的 9 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    List<List<Object>> getShopScatterPlot();
}