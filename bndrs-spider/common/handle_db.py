# coding: utf-8

from common.read_config import ReadConfig
import pymysql.cursors
from common import log

class HandleMysql:
    def __init__(self):
        self.data = ReadConfig()
        self.log=log.Logger('../../logs/handle_db.log',level='info')

    def conn_mysql(self):
        """连接数据库"""
        host = self.data.get_db("host")
        user = self.data.get_db("user")
        password = self.data.get_db("password")
        db = self.data.get_db("db")
        port = int(self.data.get_db("port"))
        try:
            self.conn = pymysql.connect(host=host, user=user, password=password, db=db, port=port)
            self.cur = self.conn.cursor()
        except Exception:
            self.log.logger.info("192.168.141.131数据库连接失败" + str(Exception))

    def execute_sql(self, sql, data):
        """执行操作数据的相关sql"""
        self.conn_mysql()
        self.cur.execute(sql, data)
        self.conn.commit()

    def search(self, sql):
        """执行查询sql"""
        self.conn_mysql()
        self.cur.execute(sql)
        return self.cur.fetchall()

    def close_mysql(self):
        """关闭数据库连接"""
        self.cur.close()
        self.conn.close()
