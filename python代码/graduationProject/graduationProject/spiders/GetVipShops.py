import scrapy


class GetVipShops(scrapy.Spider):
    def __init__(self, vip_ship_from_address=None, *args, **kwargs):
        print('init')

    def start_requests(self):
        print('执行了')
    def test(self,response):
        print(response.text)
        pass