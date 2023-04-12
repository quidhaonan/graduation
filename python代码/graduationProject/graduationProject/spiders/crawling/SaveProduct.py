import scrapy
# 用来格式化时间的模块
# 用来通过正则表达式来提取起批量的模块
import re

# 用来传递 item 对象
from graduationProject.items import GraduationprojectItem
# 获取商铺第一页内容
from graduationProject.spiders.crawingShops.GetFirstPageShopInformation import GetFirstPageShopInformation


class SaveProduct:

    def __init__(self, *args, **kwargs):
        super(SaveProduct, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.getFirstPageShopInformation = GetFirstPageShopInformation()

    # 到这里，已经是开始存储数据库了，这里爬取第一页至第二十页
    def saveProduct(self,response):
        try:
            # 获得第三分类的名字
            third_level_name = response.meta['third_level_name']
            # 获得成交额
            turnover=response.meta['turnover']

            # 全部加 if ，是因为有个别的数据是一个空数组，而此时加 [0]，会导致报数组越界的异常，如果是空数组，则在 else 中进行置空
            # 商品名称
            pro_name = response.xpath(
                '//div[@class="supply-price-show"]//div[@class="d-t"]/text()').extract()
            if pro_name:
                pro_name = pro_name[0]
            else:
                pro_name=''
            # 商品价格
            price = response.xpath(
                '//div[@class="supply-price-show"]//div[@class="active-p"]/text()').extract()
            if price:
                price = price[0]
                # 将价格变为 double 类型，xx.xx元或xx.xx-xx.xx元 --> xx.xx
                price = float(price.split('-')[0].split('元')[0])
            else:
                price=0
            # 起批量
            # start_batching = response.xpath(
            #     '//div[@class="c-ctn"]//div[@class="supply-price-show"]//div[@class="line-val"]/text()').extract()
            # 与前面的 pro_name、price 都省略了一样的前缀 //div[@class="c-ctn"]
            start_batching = response.xpath(
                '//div[@class="supply-price-show"]//div[@class="line-val"]/text()').extract()
            if start_batching:
                start_batching = start_batching[0]
                # 将起批量变为 int 类型，xx箱起批 --> xx
                start_batching = int(re.findall('\d+', start_batching)[0])
            else:
                start_batching=-1
            # 更新时间
            # update_time = response.xpath(
            #     '//div[@class="c-ctn"]//div[@class="supply-price-show"]//div[@class="r-t"]/text()').extract()
            update_time = response.xpath(
                '//div[@class="supply-price-show"]//div[@class="r-t"]/text()').extract()
            if update_time:
                update_time = update_time[0]
                # 格式化时间，有时分秒的 datetime 格式，更新时间：2023年 03月11日 --> 2023-03-11 00:00:00
                # 数据库不支持这种插入方式，因此需要改变 2023-03-11 00:00:00 --> 20230211
                # update_time=update_time[5:9]+'/'+update_time[11:13]+'/'+update_time[14:16]
                # update_time=datetime.strptime(update_time, '%Y/%m/%d')
                update_time = update_time[5:9] + update_time[11:13] + update_time[14:16]
            # 发货地址
            ship_from_address = response.xpath(
                '//div[@class="c-ctn"]//div[@class="con-bg"]/div[@class="batch-num mar flex-c"][1]//div[@class="line-val"]/text()').extract()
            if ship_from_address:
                ship_from_address = ship_from_address[0]
                shop_address_temp=ship_from_address
                # 商品详细地址，给商品的表格使用
                pro_address = ship_from_address
                # 改发货地址的格式，由吉林省长春市二道区 --> 吉林，依靠前两个字符来确定是哪个省，如果精确到区级，会导致数量太多
                ship_from_address = ship_from_address[0:2]
            # 采购热度
            purchasing_heat = response.xpath(
                '//div[@class="c-ctn"]//div[@class="con-bg"]/div[@class="batch-num mar flex-c"]//div[@class="line-val flex-center"]//img').extract()
            # 根据数组求长度，即为火花的个数
            if purchasing_heat:
                purchasing_heat = len(purchasing_heat)
            else:
                purchasing_heat=-1
            # 询价人数
            inquiry = response.xpath(
                '//div[@class="c-ctn"]//div[@class="con-bg"]/div[@class="batch-num mar flex-c"]//div[@class="line-val"][1]//span/text()').extract()
            if inquiry:
                inquiry = inquiry[0]
                # 此时的询价人数，前后有空格，需要去空格，字符串类型转为 int 类型，6 --> 6
                inquiry = int(inquiry.strip())
            else:
                inquiry=-1
            # 成交人数
            traded = response.xpath(
                '//div[@class="c-ctn"]//div[@class="con-bg"]/div[@class="batch-num mar flex-c"]//div[@class="line-val"][2]//span/text()').extract()
            if traded:
                traded = traded[0]
                # 字符串类型转为 int 类型，6 --> 6
                traded = int(traded)
            else:
                traded=-1
            # 评价人数
            assess = response.xpath(
                '//div[@class="c-ctn"]//div[@class="con-bg"]/div[@class="batch-num mar flex-c"]//div[@class="line-val"][3]//span/text()').extract()
            if assess:
                assess = assess[0]
                # 字符串类型转为 int 类型，6 --> 6
                assess = int(assess)
            else:
                assess=-1
            # 描述
            pro_desc = response.xpath('//div[@class="com-bg"]//div[@class="detail-desc"]/text()').extract()
            if pro_desc:
                pro_desc = pro_desc[0]
            else:
                pro_desc = response.xpath(
                    '//div[@class="con-bg"]//div[@class="supply-price-show"]//div[@class="d-t"]/text()').extract()
                if pro_desc:
                    pro_desc = pro_desc[0]
                else:
                    pro_desc =''
            # 商铺链接
            shop_url = response.xpath('//div[@class="shop-com"]//a/@href').extract()
            if shop_url:
                shop_url = shop_url[0]
            if not shop_url.startswith('http'):
                shop_url = 'https://www.cnhnb.com' + shop_url
            # 商品链接（新增）
            pro_url=response.url
            # 收藏人数（新增）
            collectors_counts = response.xpath('//div[@class="con-bg"]//div[@class="btn-item"]/text()').extract()
            if collectors_counts:
                collectors_counts = int(collectors_counts[0].split('应')[1].strip())
            else:
                collectors_counts = -1
            # 店铺热度（新增）
            shop_heat = response.xpath('//div[@class="content"]//div[@class="hot margin-top"]//img').extract()
            if shop_heat:
                shop_heat = len(shop_heat)
            else:
                shop_heat = -1

            product = GraduationprojectItem(third_level_name=third_level_name, pro_name=pro_name,
                                            price=price,turnover=turnover, start_batching=start_batching,
                                            update_time=update_time, ship_from_address=ship_from_address,
                                            purchasing_heat=purchasing_heat,collectors_counts=collectors_counts
                                            , inquiry=inquiry,traded=traded, assess=assess, pro_desc=pro_desc,
                                            shop_url=shop_url,pro_url=pro_url,pro_address=pro_address)
            yield product

            if shop_url.startswith('https://vip'):
                yield scrapy.Request(url=shop_url, callback=self.getFirstPageShopInformation.getShopInformation,
                                     meta={'is_vip': 1, 'ship_from_address': ship_from_address,'shop_url':shop_url,
                                           'shop_heat':shop_heat,'shop_address_temp':shop_address_temp},
                                     dont_filter=True)
            else:
                yield scrapy.Request(url=shop_url, callback=self.getFirstPageShopInformation.getShopInformation,
                                     meta={'is_vip': 0, 'ship_from_address': ship_from_address,'shop_url':shop_url,
                                           'shop_heat':shop_heat,'shop_address_temp':shop_address_temp},
                                     dont_filter=True)
        except:
            pass
