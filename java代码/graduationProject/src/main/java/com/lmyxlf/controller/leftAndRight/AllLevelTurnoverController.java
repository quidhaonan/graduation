package com.lmyxlf.controller.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelTurnover;
import com.lmyxlf.service.leftAndRight.AllLevelTurnoverService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


/**
 * 每种农产品分类下的农产品营业额分析的控制器
 *
 * @author lmy
 */
@RestController
@RequestMapping("/leftAndRight")
public class AllLevelTurnoverController {
    @Resource
    private AllLevelTurnoverService allLevelTurnoverService;

    /**
     * 获取第一级分类的信息
     *
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @GetMapping("/firstLevelTurnover")
    public List<AllLevelTurnover> getFirstLevelTurnover() {
        return allLevelTurnoverService.getFirstLevelTurnover();
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @GetMapping("/secondLevelTurnover/{levelNo}")
    public List<AllLevelTurnover> getSecondLevelTurnover(@PathVariable Integer levelNo) {
        return allLevelTurnoverService.getSecondLevelTurnover(levelNo);
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @GetMapping("/thirdLevelTurnover/{levelName}")
    public List<AllLevelTurnover> getThirdLevelTurnover(@PathVariable String levelName) {
        return allLevelTurnoverService.getThirdLevelTurnover(levelName);
    }
}