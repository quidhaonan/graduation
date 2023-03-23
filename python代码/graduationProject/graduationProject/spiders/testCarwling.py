import scrapy
from graduationProject.spiders.GetVipShops import GetVipShops
from scrapy.utils.project import get_project_settings
import random


class testCarwling(scrapy.Spider):
    name = 'test'
    start_urls = ['https://www.cnhnb.com/homepage/1629074/']


    def parse(self, response):
        print(response.text)

        # # 店铺等级
        # store_grade = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//img[@class="grade-icon"]/@src').extract()
        # if store_grade:
        #     store_grade = store_grade[0][-5:-4]
        #     # 转为 int
        #     store_grade = int(store_grade)
        # # 店铺名称
        # name = response.xpath('//div[@class="com-ctn-b"]//div[@class="s-name"]/text()').extract()
        # if name:
        #     name = name[0]
        # # 店铺主营
        # main_business = response.xpath('//div[@class="com-ctn-b"]//div[@class="s-shops"]/text()').extract()
        # if main_business:
        #     main_business = main_business[0][3:]
        # # 店铺地址
        # address = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//span[@class="line-items-text"]/text()').extract()
        # if address:
        #     address = address[0]
        # # 店铺介绍
        # introduce = response.xpath('//meta[@name="description"]/@content').extract()
        # if introduce:
        #     introduce = introduce[0]
        #     if introduce.startswith('惠农网'):
        #         introduce = ''
        # # 累计线上成交额
        # cumulative_turnover = response.xpath(
        #     '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
        # if cumulative_turnover:
        #     # 因为 店铺评分、粉丝数、累计成交额 在一块，分开不了，因此需要判断（此时累计成交额排第 3 位，且是最后一位）
        #     if len(cumulative_turnover) == 3:
        #         cumulative_turnover = cumulative_turnover[2]
        #         cumulative_turnover = cumulative_turnover.split('元')[0]
        #         if cumulative_turnover.endswith('万'):
        #             # 去除万，并转为 float，并乘以 10000
        #             cumulative_turnover = float(cumulative_turnover.split('万')[0]) * 10000
        #     else:
        #         # 有些店铺隐藏了 累计线上成交额、累计成交笔数、累计线上买家数、累计接待咨询人数、累计关注店铺人数，不存在则置为0
        #         cumulative_turnover = '0'
        # # 粉丝数
        # fans_counts = response.xpath(
        #     '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
        # if fans_counts:
        #     # 此时粉丝数不会被隐藏，因此不需要进行判断
        #     fans_counts = fans_counts[1]
        # # 平均发货速度
        # average_shipping_speed = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][1]/text()').extract()
        # if average_shipping_speed:
        #     # 此处的数据有空格，去除空格
        #     average_shipping_speed = average_shipping_speed[0].strip()
        # # 售后率
        # after_sales_rate = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][2]/text()').extract()
        # if after_sales_rate:
        #     # 此处的数据有空格，去除空格
        #     after_sales_rate = after_sales_rate[0].strip()
        # # 复购率
        # repurchase_rate = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][3]/text()').extract()
        # if repurchase_rate:
        #     # 此处的数据有空格，去除空格
        #     repurchase_rate = repurchase_rate[0].strip()
        # # 平均退款速度
        # average_refund_speed = response.xpath(
        #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][3]/text()').extract()
        # if average_refund_speed:
        #     # 此处的数据有空格，去除空格
        #     average_refund_speed = average_refund_speed[0].strip()
        # # 店铺评分
        # store_ratings = response.xpath(
        #     '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
        # if store_ratings:
        #     # 此时粉丝数不会被隐藏，因此不需要进行判断
        #     store_ratings = store_ratings[0]
        #
        #
        # print('店铺等级')
        # print(store_grade)
        # print('店铺名称')
        # print(name)
        # print('店铺主营')
        # print(main_business)
        # print('店铺地址')
        # print(address)
        # print('店铺介绍')
        # print(introduce)
        # print('累计线上成交额')
        # print(cumulative_turnover)
        # print('粉丝数')
        # print(fans_counts)
        # print('平均发货速度')
        # print(average_shipping_speed)
        # print('售后率')
        # print(after_sales_rate)
        # print('复购率')
        # print(repurchase_rate)
        # print('平均退款速度')
        # print(average_refund_speed)
        # print('店铺评分')
        # print(store_ratings)

        pass