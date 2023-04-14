package com.lmyxlf.middle;

import com.lmyxlf.entity.middle.MapOfChina;
import com.lmyxlf.mapper.middle.MapOfChinaMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MapOfChinaTest {
    @Resource
    private MapOfChinaMapper mapOfChinaMapper;

    /**
     * ≤‚ ‘ mybatis
     */
    @Test
    public void testMapOfChinaMapper(){
        List<MapOfChina> theNumberOfAgriculturalProductsInTheProvinceAndCity =
                mapOfChinaMapper.getTheNumberOfAgriculturalProductsInTheProvinceAndCity();
        System.out.println(theNumberOfAgriculturalProductsInTheProvinceAndCity);
    }
}
