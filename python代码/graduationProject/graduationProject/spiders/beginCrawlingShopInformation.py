from scrapy.cmdline import execute
from graduationProject.spiders.crawingShops.PySqlUtils import PySqlUtils
from graduationProject.spiders.GetVipShops import GetVipShops
from graduationProject.spiders.GetCommonShops import GetCommonShops

import sys
import os

# 获取商品信息
if __name__ == '__main__':
    # 数据库的连接关闭工具
    pySqlUtils = PySqlUtils()
    self = pySqlUtils.getConnection()
    sql = 'select distinct shop_url ,ship_from_address FROM product group by shop_url'
    self.cursor.execute(sql)
    # 获得返回结果
    shop_urls = self.cursor.fetchall()

    vip_ship_from_address=[]
    common_ship_from_address=[]
    for shop_url in shop_urls:
        # 判断是否是 vip，此为一个字段，并且需要根据是否 vip 来拼接链接
        # vip：https://vip999888.cnhnb.com/
        # 普通：/homepage/5629325/
        if shop_url[0].startswith('http'):
            GetVipShops.start_urls.append(shop_url[0])
            vip_ship_from_address.append(shop_url[1])
            # , dont_filter=True
        else:
            # url = 'https://www.cnhnb.com' + shop_url[0]
            # GetCommonShops.start_urls.append(url)
            pass

    # 启动项目
    sys.path.append(os.path.dirname(os.path.abspath(__file__)))  # 大括号内即把该文件路径变为绝对路径
    # 传递当前链接所处的发货地编号
    execute(["scrapy", "crawl", 'getVipShops','-a', f'vip_ship_from_address={vip_ship_from_address}'])  # 以项目example为例
    # execute(["scrapy", "crawl", 'getCommonShops'])  # 以项目example为例
