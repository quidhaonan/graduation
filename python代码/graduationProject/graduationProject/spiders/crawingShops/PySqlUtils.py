from scrapy.utils.project import get_project_settings
import pymysql

class PySqlUtils:
    # 获取数据库连接
    def getConnection(self):
        settings = get_project_settings()
        self.host = settings['DB_HOST']
        self.port = settings['DB_PORT']
        self.user = settings['DB_USER']
        self.password = settings['DB_PASSWROD']
        self.name = settings['DB_NAME']
        self.charset = settings['DB_CHARSET']
        self.connect()
        return self

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

    # 关闭数据库连接
    def closeAll(self):
        self.cursor.close()
        self.conn.close()