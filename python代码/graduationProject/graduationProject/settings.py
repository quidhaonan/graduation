# Scrapy settings for graduationProject project
#
# For simplicity, this file contains only settings considered important or
# commonly used. You can find more settings consulting the documentation:
#
#     https://docs.scrapy.org/en/latest/topics/settings.html
#     https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#     https://docs.scrapy.org/en/latest/topics/spider-middleware.html

BOT_NAME = 'graduationProject'

SPIDER_MODULES = ['graduationProject.spiders']
NEWSPIDER_MODULE = 'graduationProject.spiders'

# 解决爬取遗漏问题
AUTOTHROTTLE_ENABLED = True

# 设置请求头
HEADERS =[
   {'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9','Accept-Encoding':'gzip, deflate, br','Accept-Language':'en,zh-CN;q=0.9,zh;q=0.8','Cache-Control':'max-age=0','Connection':'keep-alive','Host':'www.cnhnb.com','If-None-Match':'bbd0-/WIA2knf/pRTYshy7fw6MzgvXsI','sec-ch-ua':'"Chromium";v="104", " Not A;Brand";v="99", "Google Chrome";v="104"','sec-ch-ua-mobile':'?0','sec-ch-ua-platform':'"Windows"','Sec-Fetch-Dest':'document','Sec-Fetch-Mode':'navigate','Sec-Fetch-Site':'same-origin','Sec-Fetch-User':'?1','Upgrade-Insecure-Requests':'1','Cookie':'deviceId=3b97125-aef5-443b-9504-1742092cc; sessionId=S_0LFI4Q2NCLXOGEUZ; Hm_lvt_91cf34f62b9bedb16460ca36cf192f4c=1679395510; SL_G_WPT_TO=eo; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; Hm_lvt_81fc10bb72f85b5a9ff93042925f6543=1679396423; Hm_lpvt_81fc10bb72f85b5a9ff93042925f6543=1679396423; Hm_lvt_0e023fed85d2150e7d419b5b1f2e7c0f=1679408448; Hm_lpvt_0e023fed85d2150e7d419b5b1f2e7c0f=1679408448; Hm_lvt_a6458082fb548e5ca7ff77d177d2d88d=1679408448; Hm_lpvt_a6458082fb548e5ca7ff77d177d2d88d=1679409974; Hm_lpvt_91cf34f62b9bedb16460ca36cf192f4c=1679412487','USER_AGENT': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.190.400 QQBrowser/11.5.5240.400'},
   {':method':' GET',':path': '/?tab=2',':scheme': 'https','accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9','accept-encoding': 'gzip, deflate, br','accept-language':' en,zh-CN;q=0.9,zh;q=0.8','cache-control': 'max-age=0','cookie': 'Hm_lvt_91cf34f62b9bedb16460ca36cf192f4c=1679473202; deviceId=750f3d1-89e2-4833-aad1-797d691a5; sessionId=S_0LFJEZHS67ITRZ93; SL_G_WPT_TO=eo; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1; Hm_lpvt_91cf34f62b9bedb16460ca36cf192f4c=1679480320','if-none-match': '"14a95-xY99cz8QPqoVmYyIRJmvbTcpGv0"','sec-ch-ua':' "Chromium";v="104", " Not A;Brand";v="99", "Google Chrome";v="104"','sec-ch-ua-mobile':' ?0','sec-ch-ua-platform':' "Windows"','sec-fetch-dest': 'document','sec-fetch-mode': 'navigate','sec-fetch-site':' same-origin','sec-fetch-user':' ?1','upgrade-insecure-requests':' 1','user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36'},
   {':method':' GET',':path':' /?tab=2',':scheme': 'https','accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7','accept-encoding': 'gzip, deflate, br','accept-language': 'en,zh-CN;q=0.9,zh;q=0.8,en-GB;q=0.7,en-US;q=0.6','cache-control': 'max-age=0','cookie': 'deviceId=04114da-1491-4918-a64d-9e18c80e0; sessionId=S_0LFJLCOA8EQQ68FQ; Hm_lvt_91cf34f62b9bedb16460ca36cf192f4c=1679483907; Hm_lvt_81fc10bb72f85b5a9ff93042925f6543=1679483908; Hm_lpvt_81fc10bb72f85b5a9ff93042925f6543=1679483915; Hm_lpvt_91cf34f62b9bedb16460ca36cf192f4c=1679483917','if-none-match':' "1491d-zcq+U4XBywCL328nBfngrjTrhWU"','sec-ch-ua': '"Microsoft Edge";v="111", "Not(A:Brand";v="8", "Chromium";v="111"','sec-ch-ua-mobile': '?0','sec-ch-ua-platform': '"Windows"','sec-fetch-dest': 'document','sec-fetch-mode': 'navigate','sec-fetch-site': 'same-origin','sec-fetch-user': '?1','upgrade-insecure-requests':' 1','user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44'},
   {':method':' GET',':path': '/?tab=2',':scheme': 'https','accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3','accept-encoding': 'gzip, deflate, br','accept-language': 'zh-CN,zh;q=0.9','cache-control': 'max-age=0','cookie': 'deviceId=e193b31-f0c8-4838-9cfb-5fc912e28; sessionId=S_0LFJLJLKWG3GWT0B; Hm_lvt_91cf34f62b9bedb16460ca36cf192f4c=1679484226; Hm_lpvt_91cf34f62b9bedb16460ca36cf192f4c=1679484226','if-none-match':' "14540-IKopEB7dVvR6a/KG/Z8tu/TKaAg"','sec-fetch-mode': 'navigate','sec-fetch-site':' none','sec-fetch-user': '?1','upgrade-insecure-requests': '1','user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.87 Safari/537.36 SLBrowser/6.0.1.8131'},
   {'Accept':' image/webp,image/apng,image/*,*/*;q=0.8','Accept-Encoding': 'gzip, deflate, br','Accept-Language': 'zh-CN,zh;q=0.9','Connection':' keep-alive','Host': 'sp0.baidu.com','Referer':' https://vip642434847.cnhnb.com/','Sec-Fetch-Mode': 'no-cors','Sec-Fetch-Site': 'cross-site','User-Agent':' Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36'}
]
# Crawl responsibly by identifying yourself (and your website) on the user-agent
#USER_AGENT = 'graduationProject (+http://www.yourdomain.com)'

# 拒绝遵守“君子协议”
# Obey robots.txt rules
ROBOTSTXT_OBEY = False
# 设置日志
# LOG_FILE='logText.log'
# REQUEST_FINGERPRINTER_IMPLEMENTATION = 'scrapy.utils.request.fingerprint.RequestFingerprint'

# Configure maximum concurrent requests performed by Scrapy (default: 16)
#CONCURRENT_REQUESTS = 32

# Configure a delay for requests for the same website (default: 0)
# See https://docs.scrapy.org/en/latest/topics/settings.html#download-delay
# See also autothrottle settings and docs
#DOWNLOAD_DELAY = 3
# The download delay setting will honor only one of:
#CONCURRENT_REQUESTS_PER_DOMAIN = 16
#CONCURRENT_REQUESTS_PER_IP = 16

# Disable cookies (enabled by default)
#COOKIES_ENABLED = False

# Disable Telnet Console (enabled by default)
#TELNETCONSOLE_ENABLED = False

# Override the default request headers:
#DEFAULT_REQUEST_HEADERS = {
#   'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
#   'Accept-Language': 'en',
#}

# Enable or disable spider middlewares
# See https://docs.scrapy.org/en/latest/topics/spider-middleware.html
#SPIDER_MIDDLEWARES = {
#    'graduationProject.middlewares.GraduationprojectSpiderMiddleware': 543,
#}

# Enable or disable downloader middlewares
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html
#DOWNLOADER_MIDDLEWARES = {
#    'graduationProject.middlewares.GraduationprojectDownloaderMiddleware': 543,
#}

# Enable or disable extensions
# See https://docs.scrapy.org/en/latest/topics/extensions.html
#EXTENSIONS = {
#    'scrapy.extensions.telnet.TelnetConsole': None,
#}

# Configure item pipelines
# See https://docs.scrapy.org/en/latest/topics/item-pipeline.html
# 开启管道
ITEM_PIPELINES = {
   'graduationProject.pipelines.GraduationprojectPipeline': 300
}

# Enable and configure the AutoThrottle extension (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/autothrottle.html
#AUTOTHROTTLE_ENABLED = True
# The initial download delay
#AUTOTHROTTLE_START_DELAY = 5
# The maximum download delay to be set in case of high latencies
#AUTOTHROTTLE_MAX_DELAY = 60
# The average number of requests Scrapy should be sending in parallel to
# each remote server
#AUTOTHROTTLE_TARGET_CONCURRENCY = 1.0
# Enable showing throttling stats for every response received:
#AUTOTHROTTLE_DEBUG = False

# Enable and configure HTTP caching (disabled by default)
# See https://docs.scrapy.org/en/latest/topics/downloader-middleware.html#httpcache-middleware-settings
#HTTPCACHE_ENABLED = True
#HTTPCACHE_EXPIRATION_SECS = 0
#HTTPCACHE_DIR = 'httpcache'
#HTTPCACHE_IGNORE_HTTP_CODES = []
#HTTPCACHE_STORAGE = 'scrapy.extensions.httpcache.FilesystemCacheStorage'

# Set settings whose default value is deprecated to a future-proof value
REQUEST_FINGERPRINTER_IMPLEMENTATION = '2.7'
TWISTED_REACTOR = 'twisted.internet.asyncioreactor.AsyncioSelectorReactor'

# 连接 mysql 的参数
# 参数中一个端口号 一个是字符集 都要注意
DB_HOST = '127.0.0.1'
# 端口号是一个整数
DB_PORT = 3306
DB_USER = 'root'
DB_PASSWROD = ''
DB_NAME = 'graduation'
# utf-8的杠不允许写
DB_CHARSET = 'utf8'