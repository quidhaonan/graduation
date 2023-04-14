package com.lmyxlf.service.bigPicture;

import com.lmyxlf.entity.bigPicture.ProductTurnover;

import java.util.List;

/**
 * 农产品总营业额的服务类接口
 *
 * @author lmy
 */
public interface ProductTurnoverService {

    /**
     *获得商品销售额大于 0 的所有商品
     *
     * @return 符合要求的 ProductTurnover 对象集合
     */
    List<ProductTurnover> getProductTurnover();
}
