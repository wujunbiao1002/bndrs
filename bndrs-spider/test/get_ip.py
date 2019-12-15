"""
请求IP爬虫中获取api
http://127.0.0.1:8000/?types=0&count=5&country=国内
http://127.0.0.1:8000/delete?ip=120.92.3.127
"""
import requests
import json
if __name__ == '__main__':
    r = requests.get('http://127.0.0.1:8888/')
    ip_ports = json.loads(r.text)
    print (ip_ports)
    ip = ip_ports[1][0]
    port = ip_ports[1][1]
    print(ip)
    print(port)
    proxies={
        'http':'http://%s:%s'%(ip,port),
        'https':'https://%s:%s'%(ip,port)
    }
    print(proxies)
    r = requests.get('https://www.fastsoso.cn',proxies=proxies)
    print(r.text)

    def delete_proxy_ip(self):
        delete_sql = """DELETE from
            `proxys` WHERE id=%s or score in (1,2,3,4,5,6)  or country ='国外'
        """
        ips = list(self.proxy_ip.values())[0].split("//")
        ip_port = ips[1].split(":")

        query_sql = "SELECT id from proxys WHERE ip='"+ip_port[0] +"' AND port='"+ip_port[1]+"'"
        data_list = self.db.search(query_sql)

        for each in list(data_list):
            try:
                self.db.execute_sql(delete_sql,[each[0]])
                self.log.logger.info("删除ip成功:", [each[0]])
            except:
                self.log.logger.info("删除ip成功:", [each[0]])
        self.db.close_mysql()

        fastsoso_content.update_proxy_ip()