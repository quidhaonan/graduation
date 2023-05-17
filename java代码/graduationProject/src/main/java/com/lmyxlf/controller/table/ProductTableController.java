package com.lmyxlf.controller.table;

import com.lmyxlf.entity.table.ProductTable;
import com.lmyxlf.entity.table.SelectProduct;
import com.lmyxlf.service.table.ProductTableService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 农产品详细信息分析的控制类
 *
 * @author lmy
 */
@RestController
@RequestMapping("/table")
public class ProductTableController {
    @Resource
    private ProductTableService productTableService;

    /**
     * 获取农产品详细信息
     *
     * @param selectProduct 查询条件
     * @return 符合要求的 ProductTable 对象集合
     */
    @PostMapping("/productTableInformation")
    public List<ProductTable> getProductTableInformation(@ModelAttribute SelectProduct selectProduct){
        return productTableService.getProductTableInformation(selectProduct);
    }
}