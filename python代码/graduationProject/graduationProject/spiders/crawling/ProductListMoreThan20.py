import scrapy
# import time

# 存储商品信息
from graduationProject.spiders.crawling.SaveProduct import SaveProduct


class ProductListMoreThan20:

    def __init__(self, *args, **kwargs):
        super(ProductListMoreThan20, self).__init__(*args, **kwargs)
        # 统一创建要用的对象
        self.save_product=SaveProduct()

    def getProduct(self,response):
        try:
            # 获取要爬取的页数
            self.crawling_counts=response.meta['crawling_counts']
            # 获得第三分类的名字
            third_level_name = response.meta['third_level_name']
            # 定义基准链接
            base_url = response.url
            # 因为此时的基准链接最后有个 / ，因此不能直接拼接，需要先去除最后一个 / ，然后再与 -0-0-0-0- 进行拼接
            # 改变了，需要 -2，不然多几个0
            base_url = base_url[:-2]

            # 此后到 for 之前为止，都是爬取第二页的数据
            # 设置爬取具体商品的基准链接
            second_base_url = 'https://www.cnhnb.com'
            # 获取第二页的所有商品的链接，因为现在后面加了成交额，所以将 xpath 提前一个等级
            all_list = response.xpath('//div[@class="supply-list"]//div[@class="supply-item"]')
            for item in all_list:
                url = second_base_url + item.xpath('.//a//@href').extract()[0]
                turnover=item.xpath('.//div[@class="turnover"]/text()').extract()
                if turnover:
                    # 获得成交额，此时有空格（剪切不用担心）
                    turnover=turnover[0]
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

            # 使用 for 循环爬取后面页数的代码
            for page in range(3, self.crawling_counts+1):
                # 要使用 str() ，python 不能字符串和数字自动转换拼接
                url = base_url + str(page)
                # time.sleep(0.2)
                yield scrapy.Request(url=url, callback=self.getPages,
                                     meta={'third_level_name': third_level_name}, dont_filter=True)
        except:
            pass

    # 爬取第三页及以后的数据
    def getPages(self,response):
        try:
            # 获得第三分类的名字
            third_level_name = response.meta['third_level_name']
            # 设置基准链接
            base_url = 'https://www.cnhnb.com'
            # 获取每页的所有商品的链接，因为现在后面加了成交额，所以将 xpath 提前一个等级
            all_list = response.xpath('//div[@class="supply-list"]//div[@class="supply-item"]')
            for item in all_list:
                url = base_url + item.xpath('.//a//@href').extract()[0]
                turnover=item.xpath('.//div[@class="turnover"]/text()').extract()
                if turnover:
                    # 获得成交额，此时有空格（剪切不用担心）
                    turnover=turnover[0]
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
        except:
            pass
