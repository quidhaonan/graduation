package com.lmyxlf.table;

import com.lmyxlf.mapper.table.ShopTableMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;


@SpringBootTest
public class ShopTableTest {
    @Resource
    private ShopTableMapper shopTableMapper;

    /**
     * 测试 mybatis
     */
    @Test
    public void testShopTableMapper(){
        // 添加查询条件后报错（增加了一个参数，此时参数不匹配）
//        List<ShopTable> shopTableInformation = shopTableMapper.getShopTableInformation();
//        for (ShopTable shopTable : shopTableInformation) {
//            System.out.println(shopTable);
//        }
    }
}