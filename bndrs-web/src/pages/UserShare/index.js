import React, { Component } from 'react';
import TextSearchList from './components/TextSearchList';

export default class Content extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="content-page">
        <TextSearchList />
      </div>
    );
  }
}
