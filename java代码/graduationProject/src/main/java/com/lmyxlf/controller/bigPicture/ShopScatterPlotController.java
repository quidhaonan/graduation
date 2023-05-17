package com.lmyxlf.controller.bigPicture;

import com.lmyxlf.service.bigPicture.ShopScatterPlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 商铺散点图的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/bigPicture")
public class ShopScatterPlotController {
    @Resource
    private ShopScatterPlotService shopScatterPlotService;

    /**
     *获得商铺表的所有商铺（共 11 个字段，其中包含商铺表能数字化比较的 9 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    @GetMapping("/shopScatterPlot")
    public List<List<Object>> getShopScatterPlot() {
        return shopScatterPlotService.getShopScatterPlot();
    }
}