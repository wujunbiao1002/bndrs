import React, { Component } from 'react';
import HotRank from './components/HotRank';

export default class IndexPage extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="index-page-page">
        <HotRank />
      </div>
    );
  }
}
