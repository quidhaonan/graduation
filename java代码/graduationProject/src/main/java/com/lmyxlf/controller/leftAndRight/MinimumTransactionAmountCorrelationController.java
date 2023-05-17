package com.lmyxlf.controller.leftAndRight;

import com.lmyxlf.service.leftAndRight.MinimumTransactionAmountCorrelationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * 最低交易金额相关性分析的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/leftAndRight")
public class MinimumTransactionAmountCorrelationController {
    @Resource
    private MinimumTransactionAmountCorrelationService minimumTransactionAmountCorrelationService;

    /**
     * 获取每个最低交易额等级的详细信息（包含收藏人数、询价人数、成交人数、评价人数、处于该等级下的农产品总数）
     *
     * @return 符合要求的 MinimumTransactionAmountCorrelation 对象集合
     */
    @GetMapping("/minimumTransactionAmountCorrelation")
    public List<Object> getMinimumTransactionAmountCorrelation(){
        return minimumTransactionAmountCorrelationService.getMinimumTransactionAmountCorrelation();
    }
}