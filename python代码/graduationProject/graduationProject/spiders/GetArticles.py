import scrapy
# import time
# from functools import partial
# 导入管道
from graduationProject.items import GraduationprojectItem

# 导入请求商品列表的函数
from graduationProject.spiders.crawling.GetProductList import GetProductList


class GetarticlesSpider(scrapy.Spider):
    name = 'GetArticles'
    allowed_domains = ['cnhnb.com']
    # 起始地址
    start_urls = ['https://www.cnhnb.com/supply/']

    def __init__(self, *args, **kwargs):
        super(GetarticlesSpider, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.get_product_list=GetProductList()
        # 设置要爬取的页数（大于等于3页）
        self.crawling_counts=20

    def parse(self, response):

        try:
            # 第一级分类和第二级分类的数据较少，因此可直接创建数据库表
            # 第三级分类，也直接建立表，但是查询的时候需要连带第三级的值传入管道，因为需要将商品表与第三极表联系起来
            # 获得页面的所有分类，包括了第二级分类和第三级分类
            all_classifications=response.xpath('//div[@class="cate-cons"]//div[@class="c-c-l"]//div[@class="cate-block"]')
            # 此处的 secondLevel 对应第二级分类，每个循环的当前项包含了第三级分类的数据

            for secondLevel in range(0,len(all_classifications)):
                # 获取第三级分类的名字，因为所有商品需要与此分类的名字绑定（该属性在数据库中唯一），此时一个 secondLevel 相当于
                #   第二级分类的一个值，其中包含了大量的第三级分类的数据，此时需要循环
                third_level_names=all_classifications[secondLevel].xpath('./div[@class="cate-block-list"]//a')
                # print(third_level_names.extract())
                for third_level in third_level_names:

                    # 此时的 thirdLevelName 即为第三级分类的值
                    third_level_name=third_level.xpath('./text()').extract()[0]
                    # 获取需要跳转的连接，根据第三级分类的值来进行归类
                    overview_url=third_level.xpath('./@href').extract()[0]
                    overview_url="https://www.cnhnb.com"+overview_url


                    # 将 getProductList 函数冻结，使他可以接收到从此传递过去的 response 对象
                    # callback = partial(getProductList.getProductList, response=response)
                    # 创建一个对象，使这个回调函数能够接收新的 response 对象，使用冻结的会导致 response 对象是旧的（创建对象放到上面去了）
                    # 通过第三级分类表的数据以及所要跳转的链接，开始请求商品总列表
                    # yield scrapy.Request(url=overviewUrl, callback=callback,meta={'thirdLevelName':thirdLevelName})
                    # time.sleep(0.2)
                    yield scrapy.Request(url=overview_url, callback=self.get_product_list.getProductList,
                                         meta={'third_level_name': third_level_name,'crawling_counts':
                                               self.crawling_counts}, dont_filter=True)
        except:
            pass
