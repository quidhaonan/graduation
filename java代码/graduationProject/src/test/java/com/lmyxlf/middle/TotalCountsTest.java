package com.lmyxlf.middle;

import com.lmyxlf.entity.middle.TotalCounts;
import com.lmyxlf.mapper.middle.TotalCountsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;


@SpringBootTest
public class TotalCountsTest {
    @Resource
    private TotalCountsMapper totalCountsMapper;

    /**
     * 测试 mybatis
     */
    @Test
    public void testTotalCountsMapper(){
        List<TotalCounts> totalCounts = totalCountsMapper.getTotalCounts();
        System.out.println(totalCounts);
    }
}