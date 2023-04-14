package com.lmyxlf.controller.bigPicture;

import com.lmyxlf.entity.bigPicture.ShopScatterPlot;
import com.lmyxlf.service.bigPicture.ShopScatterPlotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商铺散点图的控制器
 *
 * @author 刘名扬
 */
@Controller
@RequestMapping("/bigPicture")
public class ShopScatterPlotController {
    @Resource
    private ShopScatterPlotService shopScatterPlotService;

    /**
     *获得商铺表的所有商铺（共 12 个字段，其中包含商铺表能数字化比较的 10 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    @GetMapping("/shopScatterPlot")
    @ResponseBody
    public List<ShopScatterPlot> getShopScatterPlot() {
        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotService.getShopScatterPlot();
        return shopScatterPlot;
    }
}
