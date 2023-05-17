package com.lmyxlf.controller.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 每种农产品分类下的农产品数量分析的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/leftAndRight")
public class AllLevelCountsController {
    @Resource
    private AllLevelCountsService allLevelCountsService;

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @GetMapping("/firstLevelCounts")
    public List<AllLevelCounts> getFirstLevelCounts(){
        return allLevelCountsService.getFirstLevelCounts();
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @GetMapping("/secondLevelCounts/{levelNo}")
    public List<AllLevelCounts> getSecondLevelCounts(@PathVariable Integer levelNo){
        return allLevelCountsService.getSecondLevelCounts(levelNo);
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @GetMapping("/thirdLevelCounts/{levelName}")
    public List<AllLevelCounts> getThirdLevelCounts(@PathVariable String levelName){
        return allLevelCountsService.getThirdLevelCounts(levelName);
    }
}