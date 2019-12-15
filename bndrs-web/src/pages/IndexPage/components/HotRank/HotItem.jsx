import { Progress } from '@alifd/next';
import React, { Component } from 'react';

import './HotRankItem.scss';
import { Link } from 'react-router-dom';

const rankStyle = [
  { backgroundColor: '#f74444' },
  { backgroundColor: '#ff7272' },
  { backgroundColor: '#f9a4a4' },
];

class HotItem extends Component {
  render() {
    const { data } = this.props;
    return (
        <Link to={{
          pathname: "/content",
          state: { keywords: data.keywords},
        }} style={styles.item} >
        <span
          style={{
            ...styles.index,
            ...rankStyle[Number(data.rank) - 1],
          }}
        >
          {data.rank}
        </span>
        <span style={styles.keyword}>{data.keywords}</span>
        </Link>
    );
  }
}

const styles = {
  item: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    padding: 3.5,
  },
  index: {
    minWidth: 22,
    height: 22,
    padding: '0 4px',
    border: '2px solid #eee',
    color: '#fff',
    lineHeight: '18px',
    textAlign: 'center',
    borderRadius: 11,
    backgroundColor: '#c6c6c6',
    marginRight: 10,
    fontSize: 12,
  },
  keyword: {
    fontSize: 14,
    flex: 'auto',
  },
  total: {
    fontSize: 12,
    paddingLeft: 10,
    width: 40,
  },
  link: {
    fontSize: 12,
    paddingLeft: 10,
  },
};

export default HotItem;
