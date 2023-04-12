import scrapy
# import time

# 低于指定页数的处理函数
from graduationProject.spiders.crawling.ProductListLessThan20 import ProductListLessThan20
# 高于指定页数的处理函数
from graduationProject.spiders.crawling.ProductListMoreThan20 import ProductListMoreThan20
# 存储商品信息
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
        try:
            # 获取要爬取的页数
            self.crawling_counts=response.meta['crawling_counts']
            # 获得第三分类的名字
            third_level_name = response.meta['third_level_name']
            # 获取整个商品列表的页数，通过页数的大小来决定调用哪个函数，此处获取分页的最后一个按钮，根据其中的数字来判断
            last_item = response.xpath('//div[@class="l-bg"]//div[@class="pagination-bg"]//a[last()]/text()').extract()[0]

            # 此后到 if 之前为止，都是爬取第一页的数据
            # 设置基准链接
            base_url = 'https://www.cnhnb.com'
            # 获取第一页的所有商品的链接，因为现在后面加了成交额，所以将 xpath 提前一个等级
            all_list = response.xpath('//div[@class="supply-list"]//div[@class="supply-item"]')
            for item in all_list:
                url = base_url + item.xpath('.//a//@href').extract()[0]
                turnover=item.xpath('.//div[@class="turnover"]/text()').extract()
                if turnover:
                    # 获得成交额，此时有空格（剪切不用担心）
                    turnover = turnover[0]
                    # 判断是否超过万
                    if '万' in turnover:
                        turnover=float(turnover.split('交')[1].split('万')[0])*10000
                    else:
                        turnover=float(turnover.split('交')[1].split('元')[0])
                else:
                    turnover=0

                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.save_product.saveProduct,
                                     meta={'third_level_name': third_level_name,'turnover':turnover}, dont_filter=True)

            # 判断，此时需要进行强转，不然会报错
            if int(last_item) <= self.crawling_counts and int(last_item) > 1:
                url = response.url
                url = url[:-1] + '-0-0-0-0-2'
                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.productListLessThan20.getProduct,
                                     meta={'third_level_name': third_level_name}, dont_filter=True)
            elif int(last_item) > self.crawling_counts:
                url = response.url
                url = url[:-1] + '-0-0-0-0-2'
                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.productListMoreThan20.getProduct,
                                     meta={'third_level_name': third_level_name,'crawling_counts':
                                           self.crawling_counts}, dont_filter=True)
        except:
            pass
