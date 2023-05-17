package com.lmyxlf.leftAndRight;

import com.lmyxlf.entity.leftAndRight.CorrelationInformation;
import com.lmyxlf.mapper.leftAndRight.CorrelationInformationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;


@SpringBootTest
public class CorrelationInformationTest {
    @Resource
    private CorrelationInformationMapper correlationInformationMapper;

    /**
     * 测试 &lt;
     */
    @Test
    public void testCorrelationInformationMapper(){
        CorrelationInformation collectorsCountsMax =
                correlationInformationMapper.getCollectorsCountsMax(0, 2359);
        System.out.println(collectorsCountsMax);

        CorrelationInformation inquiryMax =
                correlationInformationMapper.getInquiryMax(0, 2359);
        System.out.println(inquiryMax);

        CorrelationInformation tradedMax =
                correlationInformationMapper.getTradedMax(0, 2359);
        System.out.println(tradedMax);

        CorrelationInformation assessMax =
                correlationInformationMapper.getAssessMax(0, 2359);
        System.out.println(assessMax);
    }
}