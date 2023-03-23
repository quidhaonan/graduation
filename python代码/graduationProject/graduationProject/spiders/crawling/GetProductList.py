import time

import scrapy
from scrapy.crawler import CrawlerProcess

from graduationProject.spiders.crawling.ProductListLessThan20 import ProductListLessThan20
from graduationProject.spiders.crawling.ProductListMoreThan20 import ProductListMoreThan20
from graduationProject.spiders.crawling.SaveProduct import SaveProduct

class GetProductList(object):
    # https://www.cnhnb.com/p/jiuhuang/ 例如此链接
    # 此时已经到了商品总列表，暂定义，如果页数大于 20 页的，则只爬取 20 页，低于 20 页的，则爬取全部

    def __init__(self, *args, **kwargs):
        super(GetProductList, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.save_product = SaveProduct()
        self.productListLessThan20 = ProductListLessThan20()
        self.productListMoreThan20=ProductListMoreThan20()

    def getProductList(self,response):
        # try:
            # 获得第三分类的名字
            third_level_name = response.meta['third_level_name']
            # 获取整个商品列表的页数，通过页数的大小来决定调用哪个函数，此处获取分页的最后一个按钮，根据其中的数字来判断
            last_item = response.xpath('//div[@class="l-bg"]//div[@class="pagination-bg"]//a[last()]/text()').extract()[0]

            # 此后到 if 之前为止，都是爬取第一页的数据
            # 设置基准链接
            base_url = 'https://www.cnhnb.com'
            # 获取第一页的所有商品的链接
            all_list = response.xpath('//div[@class="supply-list"]//div[@class="supply-item"]//a//@href').extract()
            for item in all_list:
                url = base_url + item

                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.save_product.saveProduct,
                                     meta={'third_level_name': third_level_name}, dont_filter=True)

            # 判断，此时需要进行强转，不然会报错
            if int(last_item) <= 99 and int(last_item) > 1:
                url = response.url
                url = url[:-1] + '-0-0-0-0-2'
                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.productListLessThan20.getProduct,
                                     meta={'third_level_name': third_level_name}, dont_filter=True)
            elif int(last_item) > 99:
                url = response.url
                url = url[:-1] + '-0-0-0-0-2'
                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.productListMoreThan20.getProduct,
                                     meta={'third_level_name': third_level_name}, dont_filter=True)
        # except:
        #     pass


    # 此时是第一页的每个商品的详情，直接爬取
    def getFirstPageProduct(self,response):
        # 获得第三分类的名字
        third_level_name=response.meta['third_level_name']
        print(response)
        pass