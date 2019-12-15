# https://www.fastsoso.cn/ 首页爬虫程序

import requests, uuid,datetime
from bs4 import BeautifulSoup
from common import config
from common import handle_db
from common import get_proxys_url
from common import log


class FastsosoIndex(object):

    """
    初始化参数
    """
    def __init__(self):
        self.server = 'https://www.fastsoso.cn'
        self.target = 'https://www.fastsoso.cn'
        self.db = handle_db.HandleMysql()
        self.proxy = get_proxys_url.GetProxysUrl()
        self.log=log.Logger('../../logs/fastsoso_index_spider.log',level='info')
        self.count = int(0)

    """
    爬取主页的url链接
    """
    def get_index_url(self):
        self.log.logger.info("启动fastsoso_index_spider.py爬取程序,获取首页链接中")
        keywords = []
        urls = []
        try:
            req = requests.get(url=self.target, headers=config.get_fastsoso_header(), proxies=self.proxy.get_proxys_ip(), timeout=5)
            html = req.text
            div_bf = BeautifulSoup(html)
            div = div_bf.find_all('div', id='home')
            a_bf = BeautifulSoup(str(div[0]))
            a = a_bf.find_all('a')
            for each in a:
                urls.append(self.server + each.get('href'))
                keywords.append(each.get_text().replace(' ', '').replace('\n', '').split('\xa0')[1])
            for index in range(7):
                menu = 'menu' + str(index+1)
                div = div_bf.find_all('div', id=menu)
                a_bf = BeautifulSoup(str(div[0]))
                a = a_bf.find_all('a')
                for each in a:
                    urls.append(self.server + each.get('href'))
                    if index+1 == 3:
                        keywords.append(each.get_text().replace(' ', '').replace('\n', '').splilt('5\xa0').split('\xa0')[1])
            self.insert_fastsoso_url(urls)
            self.insert_hot_keywords(keywords)
        except:
            self.log.logger.warning('爬取首页失败')
            return True
        return False

    """
    将爬取到的url保存在mysql数据库中
    """
    def insert_fastsoso_url(self, urls):
        for url in urls:
            sql = "insert into fastsoso_url(id,url,created_date,status) values(%s,%s,%s,%s);"
            data = ["".join(str(uuid.uuid1()).split("-")).upper(), url, datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), 'NotCrawling']
            try:
                self.db.execute_sql(sql, data)
                self.count += 1
            except:
                self.log.logger.warning('此链接已存在fastsoso_url库中：' + url)
        self.log.logger.info("爬取完成本次累计爬取https://www.fastsoso.cn首页获取链接数：" + str(self.count))
        self.db.close_mysql()

    """
    将爬取到的热词保存在mysql数据库中
    """
    def insert_hot_keywords(self, keywords):
        i = 1
        if self.delete_hot_keywords():
            for keyword in keywords:
                sql = "insert into hot_keywords(id,keywords,rank,created_date,status) values(%s,%s,%s,%s,%s);"
                data = ["".join(str(uuid.uuid1()).split("-")).upper(), keyword, i, datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), 'Normal']
                try:
                    self.db.execute_sql(sql, data)
                    i = i + 1
                    if i >= 31:
                        self.log.logger.info("保存30个热词成功")
                        return True
                except:
                    self.log.logger.warning('插入hot_keywords表热词失败：' + keyword)
            self.db.close_mysql()

    """
    删除hot_keywords中所有的热词
    """
    def delete_hot_keywords(self):
        data = [datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')]
        sql = "UPDATE `hot_keywords` SET `status` = 'Delete', updated_date = %s WHERE `status` = 'Normal';"
        try:
            self.db.execute_sql(sql, data)
            self.log.logger.info("删除热词成功")
            return True
        except:
            self.log.logger.warning('删除hot_keywords表中热词失败：')
        self.db.close_mysql()
        return False

if __name__ == "__main__":
    status = True
    fastsoso_index = FastsosoIndex()
    while status:
        status = fastsoso_index.get_index_url()



