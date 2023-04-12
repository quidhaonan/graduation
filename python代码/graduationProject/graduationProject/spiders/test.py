import scrapy


class test(scrapy.Spider):
    name = 'test'
    allowed_domains = ['cnhnb.com']
    # 起始地址
    start_urls = ['https://vip515250506.cnhnb.com/?tab=2']

    def parse(self, responses):
        print(responses.text)