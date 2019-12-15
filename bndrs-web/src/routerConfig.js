// 以下文件格式为描述路由的协议格式
// 你可以调整 routerConfig 里的内容
// 变量名 routerConfig 为 iceworks 检测关键字，请不要修改名称

import { getRouterData } from './utils/formatter';
import { asideMenuConfig } from './menuConfig';

import Content from './pages/Content';
import UserShare from './pages/UserShare';
import IndexPage from './pages/IndexPage';
import Dashboard from './pages/Dashboard';

const routerConfig = [
  {
    path: '/dashboard/monitor',
    component: Dashboard,
  },
  {
    path: '/content',
    component: Content,
  },
  {
    path: '/userShare',
    component: UserShare,
  },
  {
    path: '/index',
    component: IndexPage,
  },
];

const routerData = getRouterData(routerConfig, asideMenuConfig);

export { routerData };
