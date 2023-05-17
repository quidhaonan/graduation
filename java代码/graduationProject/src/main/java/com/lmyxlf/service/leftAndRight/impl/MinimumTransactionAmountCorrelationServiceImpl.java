package com.lmyxlf.service.leftAndRight.impl;

import com.lmyxlf.annotation.RedisAnnotation;
import com.lmyxlf.entity.leftAndRight.CorrelationInformation;
import com.lmyxlf.entity.leftAndRight.MinimumTransactionAmountCorrelation;
import com.lmyxlf.mapper.leftAndRight.CorrelationInformationMapper;
import com.lmyxlf.mapper.leftAndRight.MinimumTransactionAmountCorrelationMapper;
import com.lmyxlf.service.leftAndRight.MinimumTransactionAmountCorrelationService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 最低交易金额相关性分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class MinimumTransactionAmountCorrelationServiceImpl implements MinimumTransactionAmountCorrelationService {
    @Resource
    private MinimumTransactionAmountCorrelationMapper minimumTransactionAmountCorrelationMapper;
    @Resource
    private CorrelationInformationMapper correlationInformationMapper;

    /**
     * 获取每个最低交易额等级的详细信息（包含收藏人数、询价人数、成交人数、评价人数、处于该等级下的农产品总数）
     *
     * @return 符合要求的 MinimumTransactionAmountCorrelation 对象集合
     */
    @Override
    @RedisAnnotation({"minimumTransactionAmountCorrelation"})
    public List<Object> getMinimumTransactionAmountCorrelation() {
        // 临时存储每个等级的详细信息，此时还没有分组，数据量过多
        List<MinimumTransactionAmountCorrelation> temp =
                minimumTransactionAmountCorrelationMapper.getMinimumTransactionAmountCorrelation();
        // 存储经过分组计算后的 MinimumTransactionAmountCorrelation 对象
        List<MinimumTransactionAmountCorrelation> result=new ArrayList<>();
        // 存储最终发送回前端的值，里面的值除此字符串外，其余为 List<CorrelationInformation>
        List<Object> resultMax=new ArrayList<Object>(){{add("对结果影响最大的农产品临时信息");}};

        // 进行分组计算
        grouping(100,temp,result,resultMax,correlationInformationMapper);

        // 组装符合前端格式的数据
        return assemblyData(result, resultMax);
    }

    /**
     * 用于分组计算（result，resultMax 方法执行完后会用到）
     *
     * @param groupSize 每组包含的数据个数
     * @param temp 临时存储每个等级的详细信息，此时还没有分组，数据量过多
     * @param result 存储经过分组计算后的 MinimumTransactionAmountCorrelation 对象
     * @param resultMax 存储最终发送回前端的值，里面的值除第一个字符串外，其余为 List<CorrelationInformation>
     * @param correlationInformationMapper 报错 @Resource annotation is not supported on static fields，
     *                                     因此传入进来
     */
    private void grouping(Integer groupSize, List<MinimumTransactionAmountCorrelation> temp,
                          List<MinimumTransactionAmountCorrelation> result,
                          List<Object> resultMax,
                          CorrelationInformationMapper correlationInformationMapper){
        // 临时用于接收的对象
        MinimumTransactionAmountCorrelation tempEntity;
        // 临时存储临时信息，一个 MinimumTransactionAmountCorrelation 对象对应四个 CorrelationInformation 对象，
        //      分别为：对收藏人数（询价人数、成交人数、评价人数）影响最大的农产品名称和其值
        List<CorrelationInformation> tempMax;

        // 组数
        // int groups = temp.size()/groupSize+(temp.size()%groupSize==0?0:1);
        // 最后一组不够 100 条数据，导致最低交易金额在 X 轴没有递增，数据也对不上，因此舍去不够 100 条数据的组
        int groups = temp.size()/groupSize;
        // 给每组的数据添加 ID
        int id=1;
        // 此时 i 循环的是组数
        for(int i=0;i<groups;i++){
            // 最低交易金额
            double minimumTransactionAmountCorrelation=0;
            // 收藏人数
            double collectorsCounts=0;
            // 询价人数
            double inquiry=0;
            // 成交人数
            double traded=0;
            // 评价人数
            double assess=0;
            // 处于该最低交易额等级的农产品数
            int counts=0;

            // 此时 j 循环的是每组的第 j 个，i*groupSize+j 代表总数据（temp存储）的第 i*groupSize+j 个
            for(int j=0;j<groupSize;j++){
                // 判断是否为最后一个元素
                if(i*groupSize+j==temp.size()){
                    break;
                }
                // 对每组的每项进行求和
                minimumTransactionAmountCorrelation+=temp.get(i*groupSize+j).getMinimumTransactionAmountCorrelation();
                collectorsCounts+=temp.get(i*groupSize+j).getCollectorsCounts();
                inquiry+=temp.get(i*groupSize+j).getInquiry();
                traded+=temp.get(i*groupSize+j).getTraded();
                assess+=temp.get(i*groupSize+j).getAssess();
                counts+=temp.get(i*groupSize+j).getCounts();
            }
            // 求评价值，并创建 MinimumTransactionAmountCorrelation 对象接收
            tempEntity=new MinimumTransactionAmountCorrelation(id++,
                    (double)(Math.round(minimumTransactionAmountCorrelation/groupSize*10000))/10000,
                    (double)(Math.round(collectorsCounts/counts*10000))/10000,
                    (double)(Math.round(inquiry/counts*10000))/10000,
                    (double)(Math.round(traded/counts*10000))/10000,
                    (double)(Math.round(assess/counts*10000))/10000,
                    counts);
            result.add(tempEntity);

            // 获取每组开始的起点和数量
            int start=0;
            for(int st=0;st<result.size()-1;st++){
                start+=result.get(st).getCounts();
            }
            int cts=tempEntity.getCounts();

            // 获取每组中，对该组数据影响最大的数据临时信息，并存放在数组中发送给前端
            tempMax=new ArrayList<>(Arrays.asList(correlationInformationMapper.getCollectorsCountsMax(start,cts),
                    correlationInformationMapper.getInquiryMax(start,cts),
                    correlationInformationMapper.getTradedMax(start,cts),
                    correlationInformationMapper.getAssessMax(start,cts)));
            resultMax.add(tempMax);
        }
    }

    /**
     * 用于组装数据
     *
     * @param result 存储经过分组计算后的 MinimumTransactionAmountCorrelation 对象
     * @param resultMax 存储最终发送回前端的值，里面的值除此字符串外，其余为 List<CorrelationInformation>
     * @return 符合前端要求的数据格式
     */
    private static List<Object> assemblyData(List<MinimumTransactionAmountCorrelation> result,
                                             List<Object> resultMax){
        List<Object> minimumTransactionAmount=new ArrayList<Object>(){{add("最低交易金额");}};
        List<Object> collectorsCounts=new ArrayList<Object>(){{add("收藏人数");}};
        List<Object> inquiry=new ArrayList<Object>(){{add("询价人数");}};
        List<Object> traded=new ArrayList<Object>(){{add("成交人数");}};
        List<Object> assess=new ArrayList<Object>(){{add("评价人数");}};
        List<Object> counts=new ArrayList<Object>(){{add("处于该最低交易金额的总农产品数");}};
        List<Object> objects=new ArrayList<>();
        for (MinimumTransactionAmountCorrelation item:result){
            minimumTransactionAmount.add(String.valueOf(item.getMinimumTransactionAmountCorrelation()));
            collectorsCounts.add(item.getCollectorsCounts());
            inquiry.add(item.getInquiry());
            traded.add(item.getTraded());
            assess.add(item.getAssess());
            counts.add(item.getCounts());
        }
        objects.add(minimumTransactionAmount);
        objects.add(collectorsCounts);
        objects.add(inquiry);
        objects.add(traded);
        objects.add(assess);
        objects.add(counts);
        objects.add(resultMax);
        return objects;
    }
}