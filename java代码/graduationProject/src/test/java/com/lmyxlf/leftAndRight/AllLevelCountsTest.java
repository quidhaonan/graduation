package com.lmyxlf.leftAndRight;

import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.mapper.leftAndRight.AllLevelCountsMapper;
import com.lmyxlf.service.leftAndRight.AllLevelCountsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class AllLevelCountsTest {
    @Resource
    private AllLevelCountsMapper allLevelCountsMapper;
    @Resource
    private AllLevelCountsService allLevelCountsService;

    /**
     * 测试 mybatis
     */
    @Test
    public void testAllLevelCountsMapperGetFirstLevelCounts(){
        List<AllLevelCounts> firstLevelCounts = allLevelCountsMapper.getFirstLevelCounts();
        System.out.println(firstLevelCounts);
    }
    @Test
    public void testAllLevelCountsMapperGetSecondLevelCounts(){
        List<AllLevelCounts> secondLevelCounts = allLevelCountsMapper.getSecondLevelCounts(1);
        System.out.println(secondLevelCounts);
    }
    @Test
    public void testAllLevelCountsMapperGetThirdLevelCounts(){
        // 会出现中文乱码导致查询不到，应该使用 UTF-8
        List<AllLevelCounts> thirdLevelCounts = allLevelCountsMapper.getThirdLevelCounts("肥料");
        System.out.println(thirdLevelCounts);
    }

    /**
     * 测试 redis 缓存
     */
    @Test
    public void testAllLevelCountsServiceImplGetFirstLevelCounts(){
        List<AllLevelCounts> firstLevelCounts = allLevelCountsService.getFirstLevelCounts();
        System.out.println(firstLevelCounts);
    }
    @Test
    public void testAllLevelCountsServiceImplGetSecondLevelCounts(){
        List<AllLevelCounts> secondLevelCounts1 = allLevelCountsService.getSecondLevelCounts(1);
        System.out.println(secondLevelCounts1);
//        List<AllLevelCounts> secondLevelCounts2 = allLevelCountsService.getSecondLevelCounts(2);
//        System.out.println(secondLevelCounts2);
    }
    @Test
    public void testAllLevelCountsServiceImplGetThirdLevelCounts(){
        // 乱码，AllLevelCountsServiceImpl-getThirdLevelCounts 的打印也乱码
        List<AllLevelCounts> thirdLevelCounts1 = allLevelCountsService.getThirdLevelCounts("肥料");
        System.out.println(thirdLevelCounts1);
//        List<AllLevelCounts> thirdLevelCounts2 = allLevelCountsService.getThirdLevelCounts("农药");
//        System.out.println(thirdLevelCounts2);
    }
}
