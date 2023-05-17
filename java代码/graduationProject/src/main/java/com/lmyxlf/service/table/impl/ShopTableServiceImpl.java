package com.lmyxlf.service.table.impl;

import com.lmyxlf.entity.table.SelectShop;
import com.lmyxlf.entity.table.ShopTable;
import com.lmyxlf.mapper.table.ShopTableMapper;
import com.lmyxlf.service.table.ShopTableService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


/**
 * 商铺详细信息分析的服务类接口的实现类
 *
 * @author lmy
 */
@Service
public class ShopTableServiceImpl implements ShopTableService {
    @Resource
    private ShopTableMapper shopTableMapper;

    /**
     * 获取商铺详细信息
     *
     * @param selectShop 查询条件
     * @return 符合要求的 ShopTable 对象集合
     */
    @Override
    public List<ShopTable> getShopTableInformation(SelectShop selectShop) {
        // 自己计算分页
        int currentPage=selectShop.getCurrentPage();
        int pageSize=selectShop.getPageSize();
        selectShop.setCurrentPage((currentPage-1)*pageSize);

        List<ShopTable> shopTableInformation = shopTableMapper.getShopTableInformation(selectShop);
        // 给第一个商铺设置 获得符合要求的商铺个数，其它的商铺没必要携带此数据
        // 需要判断，防止没搜索到数据而报错
        if(shopTableInformation.size()==0){
            return null;
        }

        shopTableInformation.get(0).setTotalCounts(shopTableMapper.getTotalCounts(selectShop));
        return shopTableInformation;
    }
}