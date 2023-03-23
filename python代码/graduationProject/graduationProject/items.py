# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class GraduationprojectItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    third_level_name=scrapy.Field()
    name=scrapy.Field()
    price=scrapy.Field()
    start_batching=scrapy.Field()
    update_time=scrapy.Field()
    ship_from_address=scrapy.Field()
    purchasing_heat=scrapy.Field()
    inquiry=scrapy.Field()
    traded=scrapy.Field()
    assess=scrapy.Field()
    desc=scrapy.Field()
    shop_url=scrapy.Field()

    pass


# 商品类型的 Item
class ShopItem(scrapy.Item):
    name=scrapy.Field()
    ship_from_address=scrapy.Field()
    vip=scrapy.Field()
    main_business=scrapy.Field()
    introduce=scrapy.Field()
    address=scrapy.Field()
    shop_url=scrapy.Field()
    store_grade=scrapy.Field()
    cumulative_turnover=scrapy.Field()
    fans_counts=scrapy.Field()
    average_shipping_speed=scrapy.Field()
    after_sales_rate=scrapy.Field()
    repurchase_rate=scrapy.Field()
    average_refund_speed=scrapy.Field()
    store_ratings=scrapy.Field()
    pass