package com.lmyxlf.controller.middle;

import com.lmyxlf.entity.middle.TotalCounts;
import com.lmyxlf.service.middle.TotalCountsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 获取农产品与商铺的总数量的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/middle")
public class TotalCountsController {
    @Resource
    private TotalCountsService totalCountsService;

    /**
     * 获取农产品与商铺的总数量
     *
     * @return 符合要求的 TotalCounts 对象集合
     */
    @GetMapping("/totalCounts")
    public List<TotalCounts> getTotalCounts() {
        return totalCountsService.getTotalCounts();
    }
}