import scrapy

# 获取商铺第二页内容
from graduationProject.spiders.crawingShops.GetSecondPageShopInformation import GetSecondPageShopInformation


class GetFirstPageShopInformation:
    def __init__(self, *args, **kwargs):
        super(GetFirstPageShopInformation, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.getSecondPageShopInformation=GetSecondPageShopInformation()

    def getShopInformation(self,response):
        try:
            # 获得传递过来的数据
            is_vip = response.meta['is_vip']
            ship_from_address = response.meta['ship_from_address']
            shop_url = response.meta['shop_url']
            # 店铺热度
            shop_heat=response.meta['shop_heat']
            shop_address_temp=response.meta['shop_address_temp']

            # 店铺等级
            # shop_grade = response.xpath(
            #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//img[@class="grade-icon"]/@src').extract()
            shop_grade = response.xpath(
                '//img[@class="grade-icon"]/@src').extract()
            if shop_grade:
                shop_grade = shop_grade[0][-5:-4]
                # 转为 int
                shop_grade = int(shop_grade)
            else:
                shop_grade=-1

            # 开始爬取第二页
            yield scrapy.Request(url=shop_url + '?tab=2', callback=self.getSecondPageShopInformation.getShopInformation,
                                 meta={'is_vip': is_vip, 'ship_from_address': ship_from_address, 'shop_url': shop_url,
                                       'shop_grade':shop_grade,'shop_heat':shop_heat,'shop_address_temp':
                                       shop_address_temp}, dont_filter=True)
        except:
            pass