package com.lmyxlf.controller.table;

import com.lmyxlf.entity.table.SelectShop;
import com.lmyxlf.entity.table.ShopTable;
import com.lmyxlf.service.table.ShopTableService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 商铺详细信息分析的控制类
 *
 * @author lmy
 */
@RestController
@RequestMapping("/table")
public class ShopTableController {
    @Resource
    private ShopTableService shopTableService;

    /**
     * 获取商铺详细信息
     *
     * @param selectShop 查询条件
     * @return 符合要求的 ShopTable 对象集合
     */
    @RequestMapping("/shopTableInformation")
    public List<ShopTable> getShopTableInformation(@ModelAttribute SelectShop selectShop){
        return shopTableService.getShopTableInformation(selectShop);
    }
}