import React, { Component } from 'react';
import IceContainer from '@icedesign/container';

export default class Filter extends Component {
  render() {
    return (
      <IceContainer style={styles.container}>
        <div style={styles.category}>
          <span style={styles.label}>全部</span>
          <span style={styles.label}>视频</span>
          <span style={styles.label}>音乐</span>
          <span style={styles.label}>图片</span>
          <span style={styles.label}>文档</span>
          <span style={styles.label}>程序</span>
          <span style={styles.label}>其他</span>
          <span style={styles.label}>种子</span>
        </div>
        <div style={styles.others}>
          <span style={styles.label}>搜索耗时:0.057s， 结果约237304个</span>
          <span style={styles.label}>默认排序</span>
          <span style={styles.label}>时间排序</span>
        </div>
      </IceContainer>
    );
  }
}

const styles = {
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
  item: {
    marginRight: '10px',
  },
};
