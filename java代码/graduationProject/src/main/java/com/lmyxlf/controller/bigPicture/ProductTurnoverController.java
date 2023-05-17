package com.lmyxlf.controller.bigPicture;

import com.lmyxlf.entity.bigPicture.ProductTurnover;
import com.lmyxlf.service.bigPicture.ProductTurnoverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 农产品总营业额的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/bigPicture")
public class ProductTurnoverController {
    @Resource
    private ProductTurnoverService productTurnoverService;

    /**
     *获得商品销售额大于 0 的所有商品
     *
     * @return 符合要求的 ProductTurnover 对象集合
     */
    @GetMapping("/productTurnover")
    public List<ProductTurnover> getProductTurnover(){
        return productTurnoverService.getProductTurnover();
    }
}