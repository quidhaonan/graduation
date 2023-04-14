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
     * ≤‚ ‘ mybatis
     */
    @Test
    public void testShopScatterPlotMapper(){
        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotMapper.getShopScatterPlot();
        for(int i=0;i<100;i++){
            System.out.println(shopScatterPlot.get(i));
        }
    }

    /**
     * ≤‚ ‘ redis ª∫¥Ê
     */
    @Test
    public void testShopScatterPlotServiceImpl(){
        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotService.getShopScatterPlot();
        System.out.println(shopScatterPlot.get(0));
    }
}
