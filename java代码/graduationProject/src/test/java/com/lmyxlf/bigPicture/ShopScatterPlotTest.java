package com.lmyxlf.bigPicture;

import com.lmyxlf.entity.bigPicture.ShopScatterPlot;
import com.lmyxlf.mapper.bigPicture.ShopScatterPlotMapper;
import com.lmyxlf.service.bigPicture.ShopScatterPlotService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
public class ShopScatterPlotTest {
    @Resource
    private ShopScatterPlotMapper shopScatterPlotMapper;
    @Resource
    private ShopScatterPlotService shopScatterPlotService;

    /**
     * 测试 mybatis
     */
    @Test
    public void testShopScatterPlotMapper(){
        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotMapper.getShopScatterPlot();
        for(int i=0;i<100;i++){
            System.out.println(shopScatterPlot.get(i));
        }
    }

    /**
     * 测试 redis 缓存
     */
    @Test
    public void testShopScatterPlotServiceImpl(){
//        List<Object[]> shopScatterPlot = shopScatterPlotService.getShopScatterPlot();
//        System.out.println(Arrays.toString(shopScatterPlot.get(0)));
    }
}