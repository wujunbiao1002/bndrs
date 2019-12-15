# https://www.fastsoso.cn/ 内容页爬虫程序

import requests, uuid,datetime,time,random
from bs4 import BeautifulSoup, SoupStrainer
from common import config
from common import handle_db
from common import get_proxys_url
from common import log
from requests.adapters import HTTPAdapter


class FastsosoContent(object):
    """
    初始化参数
    """
    def __init__(self):
        self.server = 'https://www.fastsoso.cn'
        self.db = handle_db.HandleMysql()
        self.proxy = get_proxys_url.GetProxysUrl()
        self.sql = """insert into netdisk_resource
            (id,title,url,type,size,share_date,share_id,share_name,download_number,created_date,status,password) 
             values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);
            """
        self.deletes = """UPDATE fastsoso_url SET STATUS='Crawled',updated_date=%s WHERE url= %s
        """
        self.log=log.Logger('../../logs/fastsoso_content_spider.log',level='info')
        self.count = int(0)
        self.proxy_ip = ''

    """
    获取数据库中的待爬取url
    """
    def get_db_url(self):
        db_urls = []
        sql = "SELECT url FROM `fastsoso_url` WHERE `status`='NotCrawling'"
        datas = self.db.search(sql)
        for each in list(datas):
            data = list(each)
            db_urls.append(data.pop())
        return db_urls

    """
    更新代理ip
    """
    def update_proxy_ip(self):
        self.proxy_ip = self.proxy.get_proxys_ip()
        self.log.logger.info("--更换代理IP地址为--:" + str(self.proxy_ip))
        # return proxy_ip

    """
    爬取内容页内容
    """
    def get_content(self):
        self.log.logger.info("启动fastsoso_url库链接爬取程序成功")
        fastsoso_content.update_proxy_ip()
        while True:
            db_urls = self.get_db_url()
            target = random.choice(db_urls)
            # self.delete_proxy_ip()
            num = 0
            for i in range(200):
                url = "%s&page=%s" % (target, i + 1)
                try:
                    # request = requests.Session()
                    # request.mount('http://', HTTPAdapter(max_retries=2))
                    # request.mount('https://', HTTPAdapter(max_retries=2))

                    req = requests.get(url=url, headers=config.get_fastsoso_header(), proxies=self.proxy_ip,timeout=7)
                    html = req.text
                    soup = BeautifulSoup(html)

                    div = soup.select('body div > div:nth-of-type(3) > div:nth-of-type(1) > div > div')
                    share_user_url = []
                    baidu_records = []
                    for index in range(len(div)):
                        if index % 2 == 0:  # 双数下标
                            record = []
                            div_url = div[index].select('div[name="content-title"]')    # 百度云链接、标题div
                            div_pws = div[index].select('div[style="padding-top: 3px;"]')      # 密码div
                            div_content = div[index].select('div[style="color: #105207;"]')    # 百度云信息div
                            div_share = div[index].select('div[style="color:#2B91E3;"]')    # 分享人div

                            baidu_a = div_url[0].find('a')
                            record.append(baidu_a.get_text().replace(' ', '').replace('\n', ''))  # 获取标题
                            record.append(baidu_a['href'])  # 查a标签的href值  print(a.get("href")) a['href'] 获取百度云链接
                            if len(div_pws) == 0:
                                record.append("")
                            else:
                                span = div_pws[0].select('span[style="color: #0000FF;"]')
                                record.append(span[0].text)  # 获取加密，密码

                            content = div_content[0].text.replace(' ', '').replace('\n', '').split('|')
                            record.append(content[0].split('：')[1])
                            record.append(content[1].split('：')[1])
                            record.append(content[2].split('：')[1])
                            if len(div_share) == 0:
                                record.append('')
                                record.append('')
                            else:
                                share_a = div_share[0].find('a')
                                record.append(share_a.text)
                                share_url = share_a["href"]
                                share_user_url.append(self.server + share_url)
                                record.append(share_url.split('=')[1])
                            baidu_records.append(record)
                    for data in baidu_records:
                        data_new = ["".join(str(uuid.uuid1()).split("-")).upper(),data[0],data[1],data[4],data[5],data[3],
                                    data[7],data[6],0,datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), 'Normal',data[2]]
                        try:
                            self.db.execute_sql(self.sql,data_new)
                            self.count += 1
                            self.log.logger.info("本次累计爬取fastsoso_url库获取链接数："+str(self.count))
                        except:
                            self.log.logger.warning("数据库中存在此百度网盘链接" + data_new[2])
                    self.db.close_mysql()
                    self.save_fastsoso_share_usr_url(share_user_url)
                    # time.sleep(random.randrange(1, 3))
                except:
                    self.log.logger.warning("爬取fastsoso_url库链接失败:"+url)
                    num += 1
                    self.delete_proxy_ip_by_ip()
                    fastsoso_content.update_proxy_ip()
                    # time.sleep(random.randrange(1, 3))
            if num < 140:
                self.delete_fastsoso_url(target)

    """
    保存分享者链接
    """
    def save_fastsoso_share_usr_url(self,share_user_url):
        for url in share_user_url:
            sql = "insert into fastsoso_shareuser_url(id,url,created_date,status) values(%s,%s,%s,%s);"
            data = ["".join(str(uuid.uuid1()).split("-")).upper(), url,datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), 'NotCrawling']
            try:
                self.db.execute_sql(sql, data)
            except:
                self.log.logger.warning('此链接已存在fastsoso_shareuser_url库中：' + url)
        self.db.close_mysql()

    """
    删除已经爬取的链接
    """
    def delete_fastsoso_url(self,data):
        self.log.logger.info("爬取完成删除fastsoso_url库链接：" + data)
        datas = [datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'),data]
        self.db.execute_sql(self.deletes,datas)
        self.db.close_mysql()

    """
    删除代理ip
    """
    def delete_proxy_ip(self):
        delete_sql = """DELETE from
            `proxys` WHERE score in (1,2,3,4,5,6,7)  or country ='国外' OR area LIKE '%%香港%%' OR area LIKE '%%台湾%%'
        """
        try:
            self.db.execute_sql(delete_sql,[])
            self.log.logger.info("删除proxys库失效IP成功:",)
        except:
            self.log.logger.warning("删除proxys库失效IP出现异常:")
        self.db.close_mysql()

    """
    删除代理ip，根据ip删除
    """
    def delete_proxy_ip_by_ip(self):
        delete_sql = "DELETE from `proxys` WHERE ip=%s AND port=%s"
        ips = list(self.proxy_ip.values())[0].split("//")
        ip_port = ips[1].split(":")
        data = [ip_port[0],ip_port[1]]
        try:
            self.db.execute_sql(delete_sql,data)
            self.log.logger.info("删除无效代理ip:"+ str(data))
        except:
            self.log.logger.warning("删除proxys库失效IP出现异常:")

if __name__ == '__main__':
    fastsoso_content = FastsosoContent()
    while True:
        fastsoso_content.get_content()
