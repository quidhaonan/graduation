# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import scrapy
# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
# 加载 mysql 的 settings 文件
from scrapy.utils.project import get_project_settings
import pymysql
# 用来判断是不是商品类的实例
from graduationProject.items import GraduationprojectItem



class GraduationprojectPipeline:
    def open_spider(self,spider):
        settings = get_project_settings()
        self.host = settings['DB_HOST']
        self.port = settings['DB_PORT']
        self.user = settings['DB_USER']
        self.password = settings['DB_PASSWROD']
        self.name = settings['DB_NAME']
        self.charset = settings['DB_CHARSET']
        self.connect()

    def process_item(self, item, spider):
        if isinstance(item,GraduationprojectItem):
            # 查找与第三级分类表对应的外键
            sql = "select level_no from third_level where level_name='{}'".format(item['third_level_name'])
            # 执行sql语句
            self.cursor.execute(sql)
            level_no = self.cursor.fetchall()[0][0]

            # 查找地址与地址表相对应的 id，用于存储
            sql = "select province_id from province where province_name like '{}%'".format(item['ship_from_address'])
            self.cursor.execute(sql)
            ship_from_address = self.cursor.fetchall()[0][0]
            # 插入数据
            sql = "insert into product(third_level,pro_name,price,start_batching,update_time,ship_from_address,purchasing_heat," \
                  "inquiry,traded,assess,pro_desc,shop_url) values({},'{}',{},{},{},{},{},{},{},{},'{}','{}')".format(
                level_no, item['name'], item['price'], item['start_batching'], item['update_time'],
                ship_from_address, item['purchasing_heat'], item['inquiry'], item['traded'], item['assess'],
                item['desc'], item['shop_url']
            )
            self.cursor.execute(sql)
            # 提交
            self.conn.commit()

            return item
        else:
            # 查找地址与地址表相对应的 id，用于存储
            sql = "select province_id from province where province_name like '{}%'".format(item['ship_from_address'])
            self.cursor.execute(sql)
            ship_from_address = self.cursor.fetchall()[0][0]

            # 判断是否为 []
            name=item['name']
            if name==[]:
                name=''
            vip=item['vip']
            if vip==[]:
                vip=0
            main_business=item['main_business']
            if main_business==[]:
                main_business=''
            introduce=item['introduce']
            if introduce==[]:
                introduce=''
            address=item['address']
            if address==[]:
                address=''
            shop_url=item['shop_url']
            if shop_url==[]:
                shop_url=''
            store_grade=item['store_grade']
            if store_grade==[]:
                store_grade=0
            cumulative_turnover=item['cumulative_turnover']
            if cumulative_turnover==[]:
                cumulative_turnover=0.0
            fans_counts=item['fans_counts']
            if fans_counts==[]:
                fans_counts=0
            average_shipping_speed=item['average_shipping_speed']
            if average_shipping_speed==[]:
                average_shipping_speed=''
            after_sales_rate=item['after_sales_rate']
            if after_sales_rate==[]:
                after_sales_rate=''
            repurchase_rate=item['repurchase_rate']
            if repurchase_rate==[]:
                repurchase_rate=''
            average_refund_speed=item['average_refund_speed']
            if average_refund_speed==[]:
                average_refund_speed=''
            store_ratings=item['store_ratings']
            if store_ratings==[]:
                store_ratings=5.0

            # 插入数据
            sql = "insert into tshop(name,province_id,is_vip,main_business,introduce,address,shop_url,store_grade," \
                  "cumulative_turnover,fans_counts,average_shipping_speed,after_sales_rate,repurchase_rate," \
                  "average_refund_speed,store_ratings) values('{}',{},{},'{}','{}','{}','{}',{},{},{},'{}'," \
                  "'{}','{}','{}',{})".format(name, ship_from_address, vip, main_business,
                                              introduce, address, shop_url, store_grade,
                                              cumulative_turnover, fans_counts,average_shipping_speed,
                                              after_sales_rate, repurchase_rate,average_refund_speed,
                                              store_ratings)
            self.cursor.execute(sql)
            # 提交
            self.conn.commit()

            return item

    def close_spider(self,spider):
        self.cursor.close()
        self.conn.close()

    # def process_item(self, item, spider):
    #     return item

    def connect(self):
        self.conn = pymysql.connect(
            host=self.host,
            port=self.port,
            user=self.user,
            password=self.password,
            db=self.name,
            charset=self.charset
        )
        self.cursor = self.conn.cursor()
