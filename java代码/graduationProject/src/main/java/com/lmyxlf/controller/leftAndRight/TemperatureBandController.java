package com.lmyxlf.controller.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.service.leftAndRight.TemperatureBandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 温度带分析的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/leftAndRight")
public class TemperatureBandController {
    @Resource
    private TemperatureBandService temperatureBandService;

    /**
     * 获取各个温度带的出售农产品数量
     *
     * @return 符合要求的 AllLevelCounts 对象集合（因返回字段和该实体类属性相同，因此使用该实体类）
     */
    @GetMapping("/temperatureBand")
    public Map<String,List<AllLevelCounts>> getTheNumberOfProductsInEachTemperatureZone() {
        return temperatureBandService.getTheNumberOfProductsInEachTemperatureZone();
    }
}