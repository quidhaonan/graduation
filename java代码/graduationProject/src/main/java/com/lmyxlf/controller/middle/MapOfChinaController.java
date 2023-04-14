package com.lmyxlf.controller.middle;

import com.lmyxlf.entity.middle.MapOfChina;
import com.lmyxlf.service.middle.MapOfChinaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每个省市农产品的数量分析的控制器
 *
 * @author lmy
 */
@Controller
@RequestMapping("/middle")
public class MapOfChinaController {
    @Resource
    private MapOfChinaService mapOfChinaService;

    /**
     * 获得每个省市农产品的数量，制作中国地图
     *
     * @return
     */
    @GetMapping("/theNumberOfAgriculturalProductsInTheProvinceAndCity")
    @ResponseBody
    public List<MapOfChina> getTheNumberOfAgriculturalProductsInTheProvinceAndCity() {
        List<MapOfChina> theNumberOfAgriculturalProductsInTheProvinceAndCity =
                mapOfChinaService.getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
        return theNumberOfAgriculturalProductsInTheProvinceAndCity;
    }
}
