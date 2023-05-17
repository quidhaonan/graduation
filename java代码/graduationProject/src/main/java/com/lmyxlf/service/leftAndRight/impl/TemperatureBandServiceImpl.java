package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.leftAndRight.AllLevelCounts;
import com.lmyxlf.mapper.leftAndRight.AllLevelCountsMapper;
import com.lmyxlf.mapper.leftAndRight.TemperatureBandMapper;
import com.lmyxlf.service.leftAndRight.TemperatureBandService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;


/**
 * 温度带分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class TemperatureBandServiceImpl implements TemperatureBandService {
    /**
     * 热带
     */
    private final String[] tropic=new String[]{"海南省"};
    /**
     * 亚热带
     */
    private final String[] subtropics=new String[]{"上海市","江苏省","浙江省","安徽省","福建省","江西省","湖北省",
                                    "湖南省","广东省","广西壮族自治区","重庆市","四川省","贵州省","云南省",
                                    "香港特别行政区","澳门特别行政区","台湾省"};
    /**
     * 暖温带
     */
    private final String[] warmTemperateZone=new String[]{"北京市","天津市","河北省","山西省","山东省","河南省",
                                    "陕西省","甘肃省","新疆维吾尔自治区"};
    /**
     * 中温带
     */
    private final String[] mediumTemperateZone=new String[]{"内蒙古自治区","辽宁省","吉林省","黑龙江省",
                                    "宁夏回族自治区"};
    /**
     * 寒温带（仅有黑龙江和内蒙古北部小范围有，忽略）
     */
    private final String[] coldTemperateZone=new String[]{};
    /**
     * 青藏高原区
     */
    private final String[] qinghaiTibetPlateauArea=new String[]{"西藏自治区","青海省"};
    @Resource
    private AllLevelCountsMapper allLevelCountsMapper;
    @Resource
    private TemperatureBandMapper temperatureBandMapper;

    /**
     * 获取各个温度带的出售农产品数量
     *
     * @return 符合要求的 AllLevelCounts 对象集合（因返回字段和该实体类属性相同，因此使用该实体类）
     */
    @Override
    @RedisAnnotation({"temperatureBand"})
    public Map<String,List<AllLevelCounts>> getTheNumberOfProductsInEachTemperatureZone() {
        // 最终返回结果
        Map<String,List<AllLevelCounts>> map=new LinkedHashMap<>();
        // 获得每个第一级分类下的各农产品总数
        List<AllLevelCounts> firstLevelCounts = allLevelCountsMapper.getFirstLevelCounts();

        // 获得热带各分类的数量
        List<AllLevelCounts> tropicCounts =
                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(tropic);
        getPercent(tropicCounts,firstLevelCounts);
        map.put("热带",tropicCounts);
        // 获得亚热带各分类的数量
        List<AllLevelCounts> subtropicsCounts =
                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(subtropics);
        getPercent(subtropicsCounts,firstLevelCounts);
        map.put("亚热带",subtropicsCounts);
        // 获得暖温带各分类的数量
        List<AllLevelCounts> warmTemperateZoneCounts =
                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(warmTemperateZone);
        getPercent(warmTemperateZoneCounts,firstLevelCounts);
        map.put("暖温带",warmTemperateZoneCounts);
        // 获得中温带各分类的数量
        List<AllLevelCounts> mediumTemperateZoneCounts =
                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(mediumTemperateZone);
        getPercent(mediumTemperateZoneCounts,firstLevelCounts);
        map.put("中温带",mediumTemperateZoneCounts);
        // 获得青藏高原区各分类的数量
        List<AllLevelCounts> qinghaiTibetPlateauAreaCounts =
                temperatureBandMapper.getTheNumberOfProductsInEachTemperatureZone(qinghaiTibetPlateauArea);
        getPercent(qinghaiTibetPlateauAreaCounts,firstLevelCounts);
        map.put("青藏高原区",qinghaiTibetPlateauAreaCounts);

        return map;
    }

    /**
     * 求每个热度带各分类所占该分类的百分比
     *
     * @param levelCounts 每个温度带各个分类的农产品数
     * @param firstLevelCounts 每个分类下农产品总数
     */
    private void getPercent(List<AllLevelCounts> levelCounts,List<AllLevelCounts> firstLevelCounts){
        for (int i=0;i<firstLevelCounts.size();i++){
            // 查询出来的类别不够，默认置为 0
            if(levelCounts.size()<i+1 || !firstLevelCounts.get(i).getLevelNo().equals(levelCounts.get(i).getLevelNo())){
                levelCounts.add(i,new AllLevelCounts(firstLevelCounts.get(i).getLevelNo(),
                                                    firstLevelCounts.get(i).getLevelName(),
                                                0,0.0));
                continue;
            }

            // 计算求每个热度带各分类所占该分类的百分比
            levelCounts.get(i).setPercent((double)(Math.round(
                    (levelCounts.get(i).getCounts()*1.0/firstLevelCounts.get(i).getCounts())*10000)
                    )/10000);
        }
    }
}