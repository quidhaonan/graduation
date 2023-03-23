import scrapy
from graduationProject.spiders.crawingShops.GetSecondPageShopInformation import GetSecondPageShopInformation

class GetFirstPageShopInformation:
    def __init__(self, *args, **kwargs):
        super(GetFirstPageShopInformation, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.getSecondPageShopInformation=GetSecondPageShopInformation()

    def getShopInformation(self,response):
        # try:
            # 获得传递过来的数据
            vip = response.meta['vip']
            ship_from_address = response.meta['ship_from_address']
            shop_url = response.meta['shop_url']

            # 店铺等级
            store_grade = response.xpath(
                '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//img[@class="grade-icon"]/@src').extract()
            if store_grade:
                store_grade = store_grade[0][-5:-4]
                # 转为 int
                store_grade = int(store_grade)

            # 开始爬取第二页
            yield scrapy.Request(url=shop_url + '?tab=2', callback=self.getSecondPageShopInformation.getShopInformation,
                                 meta={'vip': vip, 'ship_from_address': ship_from_address, 'shop_url': shop_url,
                                       'store_grade':
                                           store_grade}, dont_filter=True)
        # except:
        #     pass