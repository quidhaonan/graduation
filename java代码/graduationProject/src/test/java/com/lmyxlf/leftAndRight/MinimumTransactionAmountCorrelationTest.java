package com.lmyxlf.leftAndRight;

import com.lmyxlf.controller.leftAndRight.MinimumTransactionAmountCorrelationController;
import com.lmyxlf.entity.leftAndRight.MinimumTransactionAmountCorrelation;
import com.lmyxlf.mapper.leftAndRight.MinimumTransactionAmountCorrelationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
public class MinimumTransactionAmountCorrelationTest {
    @Resource
    private MinimumTransactionAmountCorrelationController minimumTransactionAmountCorrelationController;
    @Resource
    private MinimumTransactionAmountCorrelationMapper minimumTransactionAmountCorrelationMapper;

    /**
     * 测试是否将数据分组
     */
    @Test
    public void testMinimumTransactionAmountCorrelationController(){
        List<Object> minimumTransactionAmountCorrelation =
                minimumTransactionAmountCorrelationController.getMinimumTransactionAmountCorrelation();
//        for (Object o : minimumTransactionAmountCorrelation) {
//            List<Object> temp=(List<Object>)o;
//            System.out.println(temp.size());
//        }
    }

    /**
     * 测试 &lt;
     */
    @Test
    public void testMinimumTransactionAmountCorrelationMapper(){
        List<MinimumTransactionAmountCorrelation> minimumTransactionAmountCorrelation =
                minimumTransactionAmountCorrelationMapper.getMinimumTransactionAmountCorrelation();
        System.out.println(minimumTransactionAmountCorrelation);
    }
}