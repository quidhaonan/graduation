package com.lmyxlf.service.table.impl;

import com.github.pagehelper.PageHelper;
import com.lmyxlf.entity.table.ProductTable;
import com.lmyxlf.entity.table.SelectProduct;
import com.lmyxlf.mapper.table.ProductTableMapper;
import com.lmyxlf.service.table.ProductTableService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 农产品详细信息分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class ProductTableServiceImpl implements ProductTableService {
    @Resource
    private ProductTableMapper productTableMapper;

    /**
     * 获取农产品详细信息
     *
     * @param selectProduct 查询条件
     * @return 符合要求的 ProductTable 对象集合
     */
    @Override
    public List<ProductTable> getProductTableInformation(SelectProduct selectProduct) {
        // 开启分页
        PageHelper.startPage(selectProduct.getCurrentPage(),selectProduct.getPageSize());

        List<ProductTable> productTableInformation = productTableMapper.getProductTableInformation(selectProduct);
        // 给第一个农产品设置 获得符合要求的农产品个数，其它的农产品没必要携带此数据
        // 需要判断，防止没搜索到数据而报错
        if(productTableInformation.size()==0){
            return null;
        }

        productTableInformation.get(0).setTotalCounts(productTableMapper.getTotalCounts(selectProduct));
        return productTableInformation;
    }
}