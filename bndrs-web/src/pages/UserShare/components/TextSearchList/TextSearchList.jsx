import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Search, Grid, Pagination, Button  } from '@alifd/next';
import qs from 'qs';
import axios from 'axios';
import '../../../../layouts/BasicLayout/components/Footer/Footer.scss';
import { withRouter } from 'react-router-dom';
import URL from '../../../../config/serverConfig';

const { Row, Col } = Grid;
@withRouter
export default class TextSearchList extends Component {
  static displayName = 'TextSearchList';
  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      pageNumber: 1,
      pageSize: 10,
      total: 0,
      userId: '',
      username: '',
      sort: '',
    };
  }
  componentWillMount() {
    try {
      this.state.userId = this.props.location.state.userId;
      this.state.username = this.props.location.state.username;
      this.search();
    } catch (e) {
      this.props.history.push('/content');
    }
  }
  //  获取数据
  search = () => {
    const url = `${URL.HOST}search/user-share.do`;
    const param = {
      userId: this.state.userId,
      pageNumber: this.state.pageNumber,
      pageSize: this.state.pageSize,
      sort: this.state.sort,
    };
    axios.post(url, qs.stringify(param), {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      },
    })
      .then((response) => {
        this.setState({
          dataSource: response.data.data,
          total: response.data.total,
          time: response.data.time,
        });
      });
  };
  //  下一页
  handlePaginationChange = (pageNunber) => {
    this.state.pageNumber = pageNunber;
    this.search();
  };
  //  修改每页显示的记录条数
  handlePageSizeChange = (pageSize) => {
    this.state.pageSize = pageSize;
    this.state.pageNumber = 1;
    this.search();
  };
  renderItem = (data, idx, all) => {
    const isLast = all.length - 1 === idx;
    const wrapperStyle = { ...styles.item };
    const informationStyle = { ...styles.information };
    if (isLast) {
      delete wrapperStyle.borderBottom;
      wrapperStyle.marginBottom = '0px';
      informationStyle.marginBottom = '0px';
    }
    return (
      <div key={idx} style={wrapperStyle}>
        <div style={styles.title}>
          <a style={styles.title} href={data.url}>{data.title}</a>
          {data.password === '' ? '' : <span style={styles.normal}>&nbsp;&nbsp;|&nbsp;&nbsp;密码：{data.password}</span>}
        </div>
        <div style={styles.desc}>
          时间：{data.share_date}&nbsp;&nbsp;|&nbsp;&nbsp;
          类别：{data.type}&nbsp;&nbsp;|&nbsp;&nbsp;
          大小：{data.size}
        </div>
      </div>
    );
  };
  changeSort = (sort) => {
    this.state.sort = sort;
    this.state.pageNumber = 1;
    this.search();
  };
  render() {
    return (
      <div className="text-search-list">
        <IceContainer style={styles.container}>
          <div style={styles.others}>
            <span style={styles.label}>搜索耗时：{this.state.time}秒， 结果约{this.state.total}个，分享者：{this.state.username}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <Button type="primary" onClick={this.changeSort.bind(this,'')}>默认排序</Button> &nbsp;&nbsp;
            <Button type="primary" onClick={this.changeSort.bind(this,'sort')}>时间排序</Button> &nbsp;&nbsp;
          </div>
        </IceContainer>
        <IceContainer className="article-list">
          {this.state.dataSource.map(this.renderItem)}
        </IceContainer>
        <span>{`共 ${this.state.total} 项`}</span>
        <Pagination
          style={styles.pagination}
          current={this.state.pageNumber}
          total={this.state.total}
          pageSize={this.state.pageSize}
          pageSizeSelector='dropdown'
          onChange={this.handlePaginationChange}
          onPageSizeChange={this.handlePageSizeChange}
        />
      </div>
    );
  }
}
const styles = {
  item: {
    borderBottom: '1px solid #F4F4F4',
    marginBottom: '15px',
  },
  title: {
    color: '#337ab7',
    fontSize: '20px',
    marginBottom: '10px',
    position: 'relative',
  },
  datetime: {
    position: 'absolute',
    right: '10px',
    fontSize: '12px',
    color: '#9B9B9B',
  },
  desc: {
    color: '#999',
    fontSize: '13px',
    lineHeight: '15px',
    paddingBottom: '10px',
  },
  information: {
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: '15px',
  },
  button: {
    marginRight: '10px',
  },
  operator: {
    paddingTop: '8px',
    fontSize: '12px',
    color: '#9B9B9B',
  },
  operatorItem: {
    marginRight: '5px',
  },
  normal: {
    color: '#00C730',
  },
  container: {},
  category: {
    padding: '0 10px 15px',
    borderBottom: '1px solid #eee',
  },
  others: {
    padding: '15px 10px 0',
  },
  label: {
    color: '#333',
    fontSize: '14px',
    marginRight: '10px',
  },
};
