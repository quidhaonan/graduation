import sys
import os

import scrapy
from scrapy.cmdline import execute
from graduationProject.spiders.crawingShops.PySqlUtils import PySqlUtils
from graduationProject.spiders.testCarwling import testCarwling


if __name__ == '__main__':
    sys.path.append(os.path.dirname(os.path.abspath(__file__)))  # 大括号内即把该文件路径变为绝对路径
    my_param = ''
    execute(["scrapy", "crawl", 'test',"-a", f"my_param={my_param}"])  # 以项目example为例



    # try:
    #     pySqlUtils = PySqlUtils()
    #     self = pySqlUtils.getConnection()
    #     sql = 'insert into shop_urls values(3)'
    #     self.cursor.execute(sql)
    #     # 获得返回结果
    #     situe = self.cursor.fetchall()
    # except:
    #     print('有异常')
    #     pass
    # else:
    #     print('没异常')
    #     pass

    # print('1')
    # print('1')
    # print('1')
    # print('1')
    # print('1')
    # print('1')
    # print('1')
