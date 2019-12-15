
import random
from common import handle_db

class GetProxysUrl(object):
    def __init__(self):
        self.db = handle_db.HandleMysql()
        
    def get_proxys_ip(self):
        urls = []  # 存放链接
        sql = "SELECT ip,port,protocol FROM `proxys`"
        datas = self.db.search(sql)
        for each in list(datas):
            data = list(each)
            ip = data.pop(0)
            port=data.pop(0)
            proxies = {
                'http': 'http://%s:%s' % (ip, port),
            }
            # 'https': 'https://%s:%s' % (ip, port),
            urls.append(proxies)
        self.db.close_mysql()
        return random.choice(urls)

