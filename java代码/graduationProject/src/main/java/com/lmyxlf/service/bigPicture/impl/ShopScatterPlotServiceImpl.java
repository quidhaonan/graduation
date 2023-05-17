package com.lmyxlf.service.bigPicture.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.bigPicture.ShopScatterPlot;
import com.lmyxlf.mapper.bigPicture.ShopScatterPlotMapper;
import com.lmyxlf.service.bigPicture.ShopScatterPlotService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 商铺散点图的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class ShopScatterPlotServiceImpl implements ShopScatterPlotService {
    @Resource
    private ShopScatterPlotMapper shopScatterPlotMapper;
    @Resource
    private RedisTemplate<String,List<ShopScatterPlot>> redisTemplate;

    /**
     *获得商铺表的所有商铺（共 11 个字段，其中包含商铺表能数字化比较的 9 个字段）
     *
     * @return 符合要求的 ShopScatterPlot 对象集合
     */
    @Override
    // redis 缓存不能强转，鉴于查询速度较快，因此不走缓存（返回值由 List<Object[]> 转为 List<List<Object>> 即可解决）
    // Resolved [org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON:
    //      java.util.ArrayList cannot be cast to [Ljava.lang.Object;; nested exception is
    //      com.fasterxml.jackson.databind.JsonMappingException: java.util.ArrayList cannot be cast to
    //      [Ljava.lang.Object; (through reference chain: java.util.ArrayList[0])]
    @RedisAnnotation({"shopScatterPlot"})
    public List<List<Object>> getShopScatterPlot() {
        List<ShopScatterPlot> shopScatterPlot = shopScatterPlotMapper.getShopScatterPlot();

        // 将列表转换为包含多个数组的列表
//        List<Object[]> arrayLists = new ArrayList<>();
        List<List<Object>> arrayLists = new ArrayList<>();
        List<Object> temp;

        for (ShopScatterPlot item : shopScatterPlot) {
//            Object[] array = new Object[]{item.getId(),item.getName(),item.getProvinceId(),item.getIsVip(),
//                    item.getShopHeat(),item.getCumulativeTurnover(),
//                    item.getFansCounts(),item.getAverageShippingSpeed(),
//                    item.getAfterSalesRate(),item.getRepurchaseRate(),
//                    item.getShopRatings()};
//            arrayLists.add(array);

            temp=new ArrayList<>(Arrays.asList(item.getId(),item.getName(),item.getProvinceId(),item.getIsVip(),
                    item.getShopHeat(),item.getCumulativeTurnover(),
                    item.getFansCounts(),item.getAverageShippingSpeed(),
                    item.getAfterSalesRate(),item.getRepurchaseRate(),
                    item.getShopRatings()));
            arrayLists.add(temp);
        }

        return arrayLists;
    }
}