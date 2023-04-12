# 用来传递 item 对象
from graduationProject.items import ShopItem


class GetSecondPageShopInformation:

    def getShopInformation(self,response):
        try:
            # 获得传过来的数据
            is_vip = response.meta['is_vip']
            ship_from_address = response.meta['ship_from_address']
            shop_url = response.meta['shop_url']
            shop_grade = response.meta['shop_grade']
            # 店铺热度
            shop_heat=response.meta['shop_heat']
            shop_address_temp=response.meta['shop_address_temp']

            # 店铺名称
            name = response.xpath('//div[@class="com-ctn-b"]//div[@class="s-name"]/text()').extract()
            if name:
                name = name[0]
            else:
                name=''
            # 店铺主营
            main_business = response.xpath('//div[@class="com-ctn-b"]//div[@class="s-shops"]/text()').extract()
            if main_business:
                main_business = main_business[0][3:]
            else:
                main_business=''
            # 店铺地址（有些地方的地址爬取不出来，因此我可以使用商品地址栏里面的发货地址当为这个店铺地址），直接获取 span 减轻压力
            # address = response.xpath(
            #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//span[@class="line-items-text"]/text()').extract()
            address = response.xpath(
                '//span[@class="line-items-text"]/text()').extract()
            if address:
                address = address[0]
            else:
                address=shop_address_temp
            # 店铺介绍
            shop_desc = response.xpath('//meta[@name="description"]/@content').extract()
            if shop_desc:
                shop_desc = shop_desc[0]
                if shop_desc.startswith('惠农网'):
                    shop_desc = ''
            # 累计线上成交额
            cumulative_turnover = response.xpath(
                '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
            if cumulative_turnover:
                # 因为 店铺评分、粉丝数、累计成交额 在一块，分开不了，因此需要判断（此时累计成交额排第 3 位，且是最后一位）
                if len(cumulative_turnover) == 3:
                    cumulative_turnover = cumulative_turnover[2]
                    cumulative_turnover = cumulative_turnover.split('元')[0]
                    if cumulative_turnover.endswith('万'):
                        # 去除万，并转为 float，并乘以 10000
                        cumulative_turnover = float(cumulative_turnover.split('万')[0]) * 10000
                    else:
                        # 没有万结尾的也转为 float
                        cumulative_turnover = float(cumulative_turnover)
                else:
                    # 有些店铺隐藏了 累计线上成交额、累计成交笔数、累计线上买家数、累计接待咨询人数、累计关注店铺人数，不存在则置为0
                    cumulative_turnover = '0'
            # 此为没爬取到
            else:
                cumulative_turnover=-1
            # 粉丝数
            fans_counts = response.xpath(
                '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
            if fans_counts:
                # 此时粉丝数不会被隐藏，因此不需要进行判断
                fans_counts = fans_counts[1]
                # 虽然没有看到过万的，但是认为有，即加
                if fans_counts.endswith('万'):
                    fans_counts=int(fans_counts.split('万')[0])*10000
                # 转为 int
                fans_counts = int(fans_counts)
            else:
                fans_counts=-1
            # 平均发货速度
            average_shipping_speed = response.xpath(
                '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][1]/text()').extract()
            if average_shipping_speed:
                # 此处的数据有空格，去除空格
                average_shipping_speed = average_shipping_speed[0].strip()
            else:
                average_shipping_speed='-1'
            # 售后率
            after_sales_rate = response.xpath(
                '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][2]/text()').extract()
            if after_sales_rate:
                # 此处的数据有空格，去除空格
                after_sales_rate = after_sales_rate[0].strip()
            else:
                after_sales_rate='-1'
            # 复购率
            repurchase_rate = response.xpath(
                '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][3]/text()').extract()
            if repurchase_rate:
                # 此处的数据有空格，去除空格
                repurchase_rate = repurchase_rate[0].strip()
            else:
                repurchase_rate='-1'
            # # 平均退款速度
            # average_refund_speed = response.xpath(
            #     '//div[@class="hsc-bg"]//div[@class="hs-line-list"]//div[@class="line-items"][3]/text()').extract()
            # if average_refund_speed:
            #     # 此处的数据有空格，去除空格
            #     average_refund_speed = average_refund_speed[0].strip()
            # else:
            #     average_refund_speed='-1'
            # 店铺评分
            shop_ratings = response.xpath(
                '//div[@class="l-com-ctn"]//div[@class="t1"]/following-sibling::div/text()').extract()
            if shop_ratings:
                # 此时粉丝数不会被隐藏，因此不需要进行判断
                shop_ratings = shop_ratings[0]
                # 转为 float
                shop_ratings = float(shop_ratings)
            else:
                shop_ratings=-1.0

            shop_item = ShopItem(name=name, ship_from_address=ship_from_address, is_vip=is_vip, main_business=main_business,
                                 shop_desc=shop_desc, address=address, shop_url=shop_url,shop_heat=shop_heat,
                                 shop_grade=shop_grade,cumulative_turnover=cumulative_turnover, fans_counts=fans_counts,
                                 average_shipping_speed=average_shipping_speed, after_sales_rate=after_sales_rate,
                                 repurchase_rate=repurchase_rate, average_refund_speed=average_refund_speed,
                                 shop_ratings=shop_ratings)
            yield shop_item
        except:
            pass
