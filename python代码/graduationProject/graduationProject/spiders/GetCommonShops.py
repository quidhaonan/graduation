import scrapy

class GetCommonShops(scrapy.Spider):
    name = 'getCommonShops'
    start_urls = []

    def parse(self, response):
        # Your spider logic goes here
        print(response)
        pass