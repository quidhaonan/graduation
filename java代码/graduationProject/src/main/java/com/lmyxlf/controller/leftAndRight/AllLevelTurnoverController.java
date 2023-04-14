package com.lmyxlf.controller.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelTurnover;
import com.lmyxlf.service.leftAndRight.AllLevelTurnoverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每种农产品分类下的农产品营业额分析的服务类接口的实现类
 *
 * @author lmy
 */
@Controller
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
    @ResponseBody
    public List<AllLevelTurnover> getFirstLevelTurnover() {
        List<AllLevelTurnover> firstLevelTurnover = allLevelTurnoverService.getFirstLevelTurnover();
        return firstLevelTurnover;
    }

    /**
     * 获取第二级分类的信息
     *
     * @param levelNo 第一级分类的 levelNo
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @GetMapping("/secondLevelTurnover/{levelNo}")
    @ResponseBody
    public List<AllLevelTurnover> getSecondLevelTurnover(@PathVariable Integer levelNo) {
        List<AllLevelTurnover> secondLevelTurnover = allLevelTurnoverService.getSecondLevelTurnover(levelNo);
        return secondLevelTurnover;
    }

    /**
     * 获取第三级分类的信息，提供信息给玫瑰图
     *
     * @param levelName 第二级分类的名称
     * @return 符合要求的 AllLevelTurnover 对象集合
     */
    @GetMapping("/thirdLevelTurnover/{levelName}")
    @ResponseBody
    public List<AllLevelTurnover> getThirdLevelTurnover(@PathVariable String levelName) {
        List<AllLevelTurnover> thirdLevelTurnover = allLevelTurnoverService.getThirdLevelTurnover(levelName);
        return thirdLevelTurnover;
    }
}
