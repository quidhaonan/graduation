package com.lmyxlf.controller.leftAndRight;


import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每种农产品分类下的农产品数量分析的控制器
 *
 * @author lmy
 */
@Controller
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
    @ResponseBody
    public List<AllLevelCounts> getFirstLevelCounts(){
        List<AllLevelCounts> firstLevelCounts = allLevelCountsService.getFirstLevelCounts();
        return firstLevelCounts;
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @GetMapping("/secondLevelCounts/{levelNo}")
    @ResponseBody
    public List<AllLevelCounts> getSecondLevelCounts(@PathVariable Integer levelNo){
        List<AllLevelCounts> secondLevelCounts = allLevelCountsService.getSecondLevelCounts(levelNo);
        return secondLevelCounts;
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelCounts 对象集合
     */
    @GetMapping("/thirdLevelCounts/{levelName}")
    @ResponseBody
    public List<AllLevelCounts> getThirdLevelCounts(@PathVariable String levelName){
        List<AllLevelCounts> thirdLevelCounts = allLevelCountsService.getThirdLevelCounts(levelName);
        return thirdLevelCounts;
    }
}
