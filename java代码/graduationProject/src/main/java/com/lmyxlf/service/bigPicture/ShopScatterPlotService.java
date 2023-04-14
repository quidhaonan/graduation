package com.lmyxlf.service.bigPicture;

import com.lmyxlf.entity.bigPicture.ShopScatterPlot;

import java.util.List;

/**
 * 商铺散点图的服务类接口
 *
 * @author lmy
 */
public interface ShopScatterPlotService {

    /**
     *获得商铺表的所有商铺（共 12 个字段，其中包含商铺表能数字化比较的 10 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    List<ShopScatterPlot> getShopScatterPlot();
}
