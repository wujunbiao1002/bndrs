import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Button } from '@alifd/next';

export default class ArticleList extends Component {
  static displayName = 'ArticleList';

  handleTagClick = (idx, text) => {
    // handler
    console.log('handleTagClick:', text);
  };

  renderTag = (text, onClick) => {
    return (
      <Button key={text} size="small" onClick={onClick} style={styles.button}>
        {text}
      </Button>
    );
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
    const shareUrl = `/search-user-share?uid=${data.share_id}`;
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
        <div style={styles.desc}>
          <a href={shareUrl}>分享达人:{data.share_name}</a>
        </div>
      </div>
    );
  };

  render() {
    const { dataSource = [] } = this.props;
    return (
      <IceContainer className="article-list">
        {dataSource.map(this.renderItem)}
      </IceContainer>
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
};
