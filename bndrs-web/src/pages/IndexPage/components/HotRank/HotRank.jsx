import Container from '@icedesign/container';
import IceContainer from '@icedesign/container';
import React, { Component } from 'react';
import { Grid, Search } from '@alifd/next';
import { withRouter } from 'react-router-dom';
import HotItem from './HotItem';
import { hashHistory } from 'react-router';
import axios from 'axios';
import qs from 'qs';
import URL from '../../../../config/serverConfig';

const { Row, Col } = Grid;

@withRouter
export default class HotRank extends Component {
  static displayName = 'HotRank';

  constructor(props) {
    super(props);
    this.state = {
      dataSource: [],
      keywords: '',
    };
  }
  onSearch = (value) => {
    this.state.keywords = value;
    const path = {
      pathname: '/content',
      state: { keywords: this.state.keywords },
    };
    this.props.history.push(path);
  };
  componentWillMount() {
    const url = `${URL.HOST}hot/query.do`;
    axios.get(url)
      .then((response) => {
        this.setState({
          dataSource: response.data.data,
        });
      });
  }
  render() {
    return (
      <div>
        <IceContainer>
          <Row>
            <Col l="16">
              <Search
                size="medium"
                style={{ width: '100%' }}
                searchText="搜索"
                placeholder="请输入要搜索的关键字"
                onSearch={this.onSearch.bind(this)}
              />
            </Col>
          </Row>
        </IceContainer>
        <Container>
          <div style={styles.header}>
            <h3 style={{ fontSize: 16, color: '#333', margin: 0 }}>
              热门搜索
            </h3>
            <span style={{ fontSize: 12, color: '#999' }}>
            数据来源：百度搜索风云榜
            </span>
          </div>

          <Row wrap>
            <Col style={{ borderRight: '1px solid #eee' }}>
              {this.state.dataSource
                .filter((item, index) => {
                  return Math.floor(index / 10) % 3 == 0; // 前10个
                })
                .map((item, index) => {
                  return <HotItem key={index} data={item}/>;
                })}
            </Col>
            <Col style={{ borderRight: '1px solid #eee' }}>
              {this.state.dataSource
                .filter((item, index) => {
                  return Math.floor(index / 10) % 3 == 1; // 前20个
                })
                .map((item, key) => {
                  return <HotItem key={key} data={item}/>;
                })}
            </Col>
            <Col>
              {this.state.dataSource
                .filter((item, index) => {
                  return Math.floor(index / 10) % 3 == 2; // 前30个
                })
                .map((item, key) => {
                  return <HotItem key={key} data={item}/>;
                })}
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

const styles = {
  header: {
    marginBottom: 20,
  },
  hotrankList: {},
};
