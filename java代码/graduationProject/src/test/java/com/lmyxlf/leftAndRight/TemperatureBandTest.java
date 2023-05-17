package com.lmyxlf.leftAndRight;

import com.lmyxlf.mapper.leftAndRight.TemperatureBandMapper;
import com.lmyxlf.service.leftAndRight.TemperatureBandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;


@SpringBootTest
public class TemperatureBandTest {
    @Resource
    private TemperatureBandMapper temperatureBandMapper;
    @Resource
    private TemperatureBandService temperatureBandService;

    /**
     * 测试 mybatis
     */
    @Test
    public void testTemperatureBandMapper(){
//        List<AllLevelCounts> theNumberOfProductsInEachTemperatureZone =
//                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(new String[]{"广东","广西"});
//        System.out.println(theNumberOfProductsInEachTemperatureZone.size());
//        theNumberOfProductsInEachTemperatureZone.forEach(System.out::println);
    }

    /**
     * 测试业务层
     */
    @Test
    public void testTemperatureBandServiceImpl(){
//        temperatureBandService.getTheNumberOfProductsInEachTemperatureZone(new Integer[]{1,2,3});
        temperatureBandService.getTheNumberOfProductsInEachTemperatureZone();
    }
}