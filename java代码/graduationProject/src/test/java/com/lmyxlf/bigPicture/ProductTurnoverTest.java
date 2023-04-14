package com.lmyxlf.bigPicture;

import com.lmyxlf.entity.bigPicture.ProductTurnover;
import com.lmyxlf.mapper.bigPicture.ProductTurnoverMapper;
import com.lmyxlf.service.bigPicture.ProductTurnoverService;
import com.lmyxlf.service.bigPicture.impl.ProductTurnoverServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ProductTurnoverTest {
    @Resource
    private ProductTurnoverMapper productTurnoverMapper;
    @Resource
    private ProductTurnoverService productTurnoverService;

    /**
     * ≤‚ ‘ mybatis
     */
    @Test
    public void testProductTurnoverMapper(){
        List<ProductTurnover> productTurnover = productTurnoverMapper.getProductTurnover();
        // 61226
        System.out.println(productTurnover.size());
    }

    /**
     * ≤‚ ‘ redis ª∫¥Ê
     */
    @Test
    public void testProductTurnoverServiceImpl(){
        List<ProductTurnover> productTurnover = productTurnoverService.getProductTurnover();
        System.out.println(productTurnover.get(0));
    }
}
